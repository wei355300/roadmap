package com.mantas.tapd.service;

import com.mantas.tapd.bug.Bug;
import com.mantas.tapd.bug.BugService;
import com.mantas.tapd.dto.*;
import com.mantas.tapd.dto.mapper.TraceConvert;
import com.mantas.tapd.exception.TapdException;
import com.mantas.tapd.iteration.Iteration;
import com.mantas.tapd.iteration.IterationService;
import com.mantas.tapd.project.Project;
import com.mantas.tapd.project.ProjectService;
import com.mantas.tapd.story.Story;
import com.mantas.tapd.story.StoryService;
import com.mantas.tapd.task.Task;
import com.mantas.tapd.task.TaskService;
import com.mantas.tapd.user.Role;
import com.mantas.tapd.user.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
public class WorkerBoardService {

    private RoleService      roleService;
    private IterationService iterationService;
    private ProjectService   projectService;
    private StoryService     storyService;
    private TaskService      taskService;
    private BugService       bugService;

    public WorkerBoardService(RoleService roleService,
                              IterationService iterationService,
                              ProjectService projectService,
                              StoryService storyService,
                              TaskService taskService,
                              BugService bugService) {
        this.roleService = roleService;
        this.iterationService = iterationService;
        this.projectService = projectService;
        this.storyService = storyService;
        this.taskService = taskService;
        this.bugService = bugService;
    }

    /**
     * 获取 tapd 上的所有项目的基本信息, 包括:
     * - 开启状态的迭代
     * - 项目中所有角色
     */
    public List<ProjectComp> getProjects() {
        List<ProjectComp> comps = new ArrayList<>();
        List<Project> projects = projectService.getProjects();
        if (Objects.isNull(projects)) {
            return Collections.EMPTY_LIST;
        }
        //通过多线程异步的形式, 缩短获取多项目的时间消耗
        ExecutorService executorService = Executors.newFixedThreadPool(projects.size());
        CompletableFuture[] futures = projects.stream()
                .map(project -> CompletableFuture.supplyAsync(() -> getProjectComps(project), executorService)
                        .whenComplete((result, t) -> comps.add(result)))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        return comps;
    }

    /**
     * 获取指定的项目的参数的数据, 包括:
     *  - 需求
     *  - 任务
     *  - 缺陷
     *
     * 并按人员的纬度进行数据分组
     */
    public Collection<Worker> getTraces(List<ProjectComp> projects) {
        Map<String, Worker> workerTraces = new HashMap<>();
        //通过多线程异步的形式, 缩短获取多项目的时间消耗
        ExecutorService executorService = Executors.newFixedThreadPool(projects.size());
        CompletableFuture[] futures = projects.stream()
                .map(project -> CompletableFuture.supplyAsync(() -> getTracesByProject(workerTraces, project), executorService)
                        .whenComplete((result, t) -> mergeTrace(workerTraces, result)))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();

        Comparator<Worker> traceComparator = Comparator.comparingInt(w -> w.getTraces().size());
        return workerTraces.values().stream().filter(w -> w.getTraces().size() > 0).sorted(traceComparator.reversed()).collect(Collectors.toList());
    }

    private void mergeTrace(Map<String, Worker> workerTraces, Collection<Worker> traces) {
        if (CollectionUtils.isEmpty(traces)) {
            return;
        }
        traces.forEach(t -> {
            Worker workerTrace = workerTraces.get(t.getUser());
            if (Objects.isNull(workerTrace)) {
                workerTraces.put(t.getUser(), t);
            }
            else {
                workerTrace.getTraces().addAll(t.getTraces());
            }
        });
    }

    private Collection<Worker> getTracesByProject(Map<String, Worker> workerTraces, ProjectComp project) {

        //获取所有项目中的员工
        //获取角色中的员工
        List<Worker> workers = roleService.getUsersByProject(project.getId(), project.getRoles());
        putWorkers(workers, workerTraces);

        //时间与状态为一组过滤条件
        if (Objects.nonNull(project.getStartDate()) || Objects.nonNull(project.getStatus())) {
            return getTracesByDateAndStatus(workerTraces, project);
        }
        //迭代为一组过滤条件
        return getTracesByIteration(workerTraces, project);
    }

    private Collection<Worker> getTracesByIteration(Map<String, Worker> workerTraces, ProjectComp project) {
        //获取项目迭代中的需求, 任务, 缺陷
        //需求按处理人分配
        List<List<Story>> stories = storyService.getByIterations(project.getId(), project.getIterations());
        assignStoryMul(stories, workerTraces);
        //任务按开发人员分配
        List<List<Task>> tasks = taskService.getByIterations(project.getId(), project.getIterations());
        assignTask(tasks, workerTraces);
        //缺陷按开发人员/测试人员分配
        List<List<Bug>> bugs = bugService.getByIterations(project.getId(), project.getIterations());
        assignBug(project, bugs, workerTraces);
        //按处理人, 开发人都关系, 将任务集合到员工列表中
        return workerTraces.values();
    }

    private Collection<Worker> getTracesByDateAndStatus(Map<String, Worker> workerTraces, ProjectComp project) {
        List<Story> stories = storyService.getByDate(project.getId(), project.getStartDate(), project.getEndDate(), project.getStatus());
        assignStory(stories, workerTraces);
        return workerTraces.values();
    }

    private void assignStoryMul(List<List<Story>> stories, Map<String, Worker> traces) {
        stories.forEach(sl -> {
            sl.forEach(s -> {
                Trace trace = TraceConvert.INSTANCE.toTrace(s);
                assignToWorker(traces, s.getOwner(), trace);
            });
        });
    }

    private void assignStory(List<Story> stories, Map<String, Worker> traces) {
        stories.forEach(s -> {
            Trace trace = TraceConvert.INSTANCE.toTrace(s);
            assignToWorker(traces, s.getOwner(), trace);
        });
    }

    private void assignTask(List<List<Task>> tasks, Map<String, Worker> traces) {
        tasks.forEach(tl -> {
            tl.forEach(t -> {
                Trace trace = TraceConvert.INSTANCE.toTrace(t);
                assignToWorker(traces, t.getOwner(), trace);
            });
        });
    }

    private void assignBug(Project project, List<List<Bug>> bugs, Map<String, Worker> traces) {
        bugs.forEach(bl -> {
            bl.forEach(b -> {
                b.setProjectId(project.getId());
                //按 测试人员/开发人员 分配
                Trace trace = TraceConvert.INSTANCE.toTrace(b);
                //测试人员
                assignToWorker(traces, b.getTester(), trace);
                //开发人员
                assignToWorker(traces, b.getDeveloper(), trace);
            });
        });
    }

    private void assignToWorker(Map<String, Worker> allWorkers, List<String> workers, Trace trace) {
        if (Objects.isNull(workers)) {
            return;
        }
        for (String worker : workers) {
            assignToWorker(allWorkers, worker, trace);
        }
    }

    private void assignToWorker(Map<String, Worker> allWorkers, String worker, Trace trace) {
        Worker workerTrace = allWorkers.get(worker);
        if (Objects.isNull(workerTrace)) {
            return;
        }
        workerTrace.getTraces().add(trace);
    }

    private void putWorkers(List<Worker> workers, Map<String, Worker> traces) {
        workers.forEach(w -> {
            Worker wt = traces.get(w.getUser());
            if (Objects.isNull(wt)) {
                traces.put(w.getUser(), w);
            }
        });
    }

    /**
     * 获取项目中开启状态的迭代及所有的角色
     */
    private ProjectComp getProjectComps(Project project) {
        ProjectComp projectComp = new ProjectComp(project);

        Integer projectId = project.getId();
        try {
            List<Role> roles = roleService.getRolesByProject(projectId);
            List<Iteration> iterations = iterationService.getIterations(projectId);

            projectComp.setIterations(iterations);
            projectComp.setRoles(roles);
        }
        catch (TapdException e) {
            log.warn("", e);
        }
        return projectComp;
    }
}
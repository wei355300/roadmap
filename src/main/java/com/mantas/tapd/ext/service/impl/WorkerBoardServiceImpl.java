package com.mantas.tapd.ext.service.impl;

import com.mantas.tapd.ext.dto.*;
import com.mantas.tapd.ext.dto.mapper.TraceConvert;
import com.mantas.tapd.ext.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WorkerBoardServiceImpl implements WorkerBoardService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private IterationService iterationService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private StoryService storyService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private BugService bugService;

    /**
     * 获取 tapd 上的所有项目的基本信息, 包括:
     * - 开启状态的迭代
     * - 项目中所有角色
     */
    @Override
    public List<ProjectComp> getProjects() {
        List<ProjectComp> comps = new ArrayList<>();
        List<Project> projects = projectService.getProjects();
        if (Objects.isNull(projects)) {
            return Collections.EMPTY_LIST;
        }
        //通过多线程异步的形式, 缩短获取多项目的时间消耗
        ExecutorService executorService = Executors.newFixedThreadPool(projects.size());
        CompletableFuture[] futures = projects.stream()
                .map(project -> CompletableFuture.supplyAsync(() -> {
                    log.info("开始跑异步: " + project.getName());
                    return getProjectComps(project);
                }, executorService).whenComplete((result, t) -> {
                    log.info("完成: " + project.getName());
                    comps.add(result);
                })).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        log.info("跑完了");
        return comps;
    }

    @Override
    public Collection<Worker> getTraces(List<ProjectComp> projects) {
        Map<String, Worker> workerTraces = new HashMap<>();
        //通过多线程异步的形式, 缩短获取多项目的时间消耗
        ExecutorService executorService = Executors.newFixedThreadPool(projects.size());
        CompletableFuture[] futures = projects.stream()
                .map(project -> CompletableFuture.supplyAsync(() -> {
                    log.info("开始跑异步: " + project.getName());
                    return getTracesByProject(workerTraces, project);
                }, executorService).whenComplete((result, t) -> {
                    log.info("完成: " + project.getName());
                    mergeTrace(workerTraces, result);
                })).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        log.info("跑完了");

        Comparator<Worker> traceComparator = Comparator.comparingInt(w -> w.getTraces().size());

        return workerTraces.values().stream().sorted(traceComparator.reversed()).collect(Collectors.toList());
    }

    private void mergeTrace(Map<String, Worker> workerTraces, Collection<Worker> traces) {
        if (CollectionUtils.isEmpty(traces)) {
            return;
        }
        traces.forEach(t -> {
            Worker workerTrace = workerTraces.get(t.getUser());
            if (Objects.isNull(workerTrace)) {
                workerTraces.put(t.getUser(), t);
            } else {
                workerTrace.getTraces().addAll(t.getTraces());
            }
        });
    }

    private Collection<Worker> getTracesByProject(Map<String, Worker> workerTraces, ProjectComp project) {
        //获取所有项目中的员工
        //获取角色中的员工
        List<Worker> workers = roleService.getUsersByProject(project.getId(), project.getRoles());
        putWorkers(workers, workerTraces);
        //获取项目迭代中的需求, 任务, 缺陷
        //需求按处理人分配
        List<List<Story>> stories = storyService.getByIterations(project.getId(), project.getIterations());
        assignStory(stories, workerTraces);
        //任务按开发人员分配
        List<List<Task>> tasks = taskService.getByIterations(project.getId(), project.getIterations());
        assignTask(tasks, workerTraces);
        //缺陷按开发人员/测试人员分配
        List<List<Bug>> bugs = bugService.getByIterations(project.getId(), project.getIterations());
        assignBug(bugs, workerTraces);
        //按处理人, 开发人都关系, 将任务集合到员工列表中
        return workerTraces.values();
    }

    private void assignStory(List<List<Story>> stories, Map<String, Worker> traces) {
        stories.forEach(sl -> {
            sl.forEach(s -> {
                Trace trace = TraceConvert.INSTANCE.toTrace(s);
                assignToWorker(traces, s.getOwner(), trace);
            });
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

    private void assignBug(List<List<Bug>> bugs, Map<String, Worker> traces) {
        bugs.forEach(bl -> {
            bl.forEach(b -> {
                //按 测试人员/开发人员 分配
                Trace trace = TraceConvert.INSTANCE.toTrace(b);
                //测试人员
                assignToWorker(traces, b.getTester(), trace);
                //开发人员
                assignToWorker(traces, b.getDeveloper(), trace);
            });
        });
    }

    private void assignToWorker(Map<String, Worker> allWorkers, String[] workers, Trace trace) {
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
        Integer projectId = project.getId();
        List<Role> roles = roleService.getRolesByProject(projectId);
        List<Iteration> iterations = iterationService.getIterationsByProject(projectId);

        ProjectComp projectComp = new ProjectComp(project);
        projectComp.setIterations(iterations);
        projectComp.setRoles(roles);
        return projectComp;
    }
}

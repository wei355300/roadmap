package com.mantas.tapd.ext.service.impl;

import com.mantas.tapd.ext.dto.*;
import com.mantas.tapd.ext.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class WorkerBoardServiceImpl implements WorkerBoardService {

    private RoleService roleService;
    private IterationService iterationService;
    private ProjectService projectService;
    private StoryService storyService;
    private TaskService taskService;
    private BugService bugService;

    @Autowired
    public WorkerBoardServiceImpl(ProjectService projectService,
                                  StoryService storyService,
                                  RoleService roleService,
                                  IterationService iterationService,
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
    @Override
    public List<ProjectComp> getProjects() {
        List<ProjectComp> comps = new ArrayList<>();
        List<Project> projects = projectService.getProjects();
        //通过多线程异步的形式, 缩短获取多项目的时间消耗
        ExecutorService executorService = Executors.newFixedThreadPool(projects.size());
        CompletableFuture[] futures = projects.stream()
                .map(project -> CompletableFuture.supplyAsync(() -> {
                    System.out.println("开始跑异步: " + project.getName());
                    return getProjectComps(project);
                }, executorService).whenComplete((result, t) -> {
                    System.out.println("完成: " + project.getName());
                    comps.add(result);
                })).toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        System.out.println("跑完了");
        return comps;
    }

    @Override
    public List<Trace> getTraces(List<ProjectComp> projects) {
        //todo
        //获取所有项目中的员工
        Integer projectId = 0;
        List<Worker> workers = roleService.getWorkersByRole(projectId);
        //获取项目迭代中的需求, 任务, 缺陷
//        storyService.getByIterations(projectId, iterations);
//        taskService.getByIterations(projectId, iterations);
//        bugService.getByIterations(projectId, iterations);
        //按处理人, 开发人都关系, 将任务集合到员工列表中
        return null;
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

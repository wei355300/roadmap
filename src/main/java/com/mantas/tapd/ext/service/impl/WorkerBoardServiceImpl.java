package com.mantas.tapd.ext.service.impl;

import com.mantas.tapd.ext.dto.Iteration;
import com.mantas.tapd.ext.dto.Project;
import com.mantas.tapd.ext.dto.ProjectComp;
import com.mantas.tapd.ext.dto.Role;
import com.mantas.tapd.ext.service.IterationService;
import com.mantas.tapd.ext.service.ProjectService;
import com.mantas.tapd.ext.service.RoleService;
import com.mantas.tapd.ext.service.WorkerBoardService;
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

    @Autowired
    public WorkerBoardServiceImpl(RoleService roleService, IterationService iterationService, ProjectService projectService) {
        this.roleService = roleService;
        this.iterationService = iterationService;
        this.projectService = projectService;
    }

    @Override
    public List<ProjectComp> getProjects() {
        List<ProjectComp> comps = new ArrayList<>();
        List<Project> projects = projectService.getProjects();
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

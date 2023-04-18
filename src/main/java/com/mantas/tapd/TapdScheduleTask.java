package com.mantas.tapd;

import com.mantas.tapd.dto.Worker;
import com.mantas.tapd.exception.TapdException;
import com.mantas.tapd.iteration.IterationService;
import com.mantas.tapd.project.Project;
import com.mantas.tapd.project.ProjectService;
import com.mantas.tapd.user.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TapdScheduleTask {

    private ProjectService projectService;
    private IterationService iterationService;
    private RoleService roleService;

    public TapdScheduleTask(ProjectService projectService, IterationService iterationService, RoleService roleService) {
        this.projectService = projectService;
        this.iterationService = iterationService;
        this.roleService = roleService;
    }

//    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS)
    public void closeIteration() {
        log.info("---执行关闭迭代任务");
        try {
            List<Project> projects = projectService.getProjects();
            for (Project p : projects) {
                if (p.getId() == (22993481)) {
                    log.info("跳过项目 {} ", p.getName());
                    continue;
                }
                log.info("关闭项目 {} 的迭代.", p.getName());
                iterationService.close(p.getId(), 14, pickUserOfProject(p.getId()));
            }
        } catch (TapdException e) {
            throw new RuntimeException(e);
        }
        log.info("关闭迭代任务结束---");

    }

    private String pickUserOfProject(Integer projectId) {
        List<Worker> workers = roleService.getUsersByProject(projectId);
        for (Worker w : workers) {
            if("韦峰".equals(w.getUser())) {
                return w.getUser();
            }
        }
        return workers.get(0).getUser();
    }
}

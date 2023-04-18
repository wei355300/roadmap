package com.mantas.tapd.controller;

import com.mantas.controller.R;
import com.mantas.tapd.controller.req.GetTraceProjectParam;
import com.mantas.tapd.controller.req.ParamHelper;
import com.mantas.tapd.dto.ProjectComp;
import com.mantas.tapd.dto.Worker;
import com.mantas.tapd.service.WorkerBoardService;
import com.mantas.tapd.workspace.WorkspaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 任务看板相关接口
 */
@Slf4j
@RestController
@RequestMapping("/api/tapd/worker/board")
public class WorkerBoardController {

    private WorkerBoardService workerBoardService;
//    private WorkspaceService workspaceService;

    @Autowired
    public WorkerBoardController(WorkerBoardService workerBoardService) {
        this.workerBoardService = workerBoardService;
//        this.workspaceService = workspaceService;
    }

    /**
     * 获取 tapd 的所有项目列表,
     * 包括各个项目中未关闭的迭代列表及角色列表
     */
    @GetMapping("/projects")
    public R getProjects() {
        List<ProjectComp> projects = workerBoardService.getProjects();
        return R.success(projects);
    }

    /**
     *
     * 获取 tapd 的所有的处理项目列表
     */
    @PostMapping("/traces")
    public R getTraces(@RequestBody @Validated List<GetTraceProjectParam> params) {
        Collection<Worker> traces = workerBoardService.getTraces(ParamHelper.toProjects(params));
        return R.success(traces);
    }
}

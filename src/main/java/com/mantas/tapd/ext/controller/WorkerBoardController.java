package com.mantas.tapd.ext.controller;

import com.mantas.base.R;
import com.mantas.tapd.ext.controller.req.GetTraceProjectParam;
import com.mantas.tapd.ext.controller.req.ParamHelper;
import com.mantas.tapd.ext.dto.ProjectComp;
import com.mantas.tapd.ext.dto.Trace;
import com.mantas.tapd.ext.dto.WorkerTrace;
import com.mantas.tapd.ext.service.WorkerBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    public WorkerBoardController(WorkerBoardService workerBoardService) {
        this.workerBoardService = workerBoardService;
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
     * 获取 tapd 的所有的处理项目列表
     */
    @GetMapping("/traces")
    public R getTraces(@RequestBody @Validated List<GetTraceProjectParam> params) {
        Collection<WorkerTrace> traces = workerBoardService.getTraces(ParamHelper.toProjects(params));
        return R.success(traces);
    }
}

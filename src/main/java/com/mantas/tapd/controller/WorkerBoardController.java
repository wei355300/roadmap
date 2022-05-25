package com.mantas.tapd.controller;

import com.mantas.controller.R;
import com.mantas.tapd.controller.req.GetTraceProjectParam;
import com.mantas.tapd.controller.req.ParamHelper;
import com.mantas.tapd.dto.ProjectComp;
import com.mantas.tapd.dto.Worker;
import com.mantas.tapd.service.WorkerBoardService;
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
     *
     * 获取 tapd 的所有的处理项目列表
     */
    @PostMapping("/traces")
    public R getTraces(@RequestBody @Validated List<GetTraceProjectParam> params) {
        // restful 风格决定获取数据应当使用 get 请求,
        // 但get 请求不支持直接传递 json 格式的参数, 所以采用 post 请求上传 body 传递, 以便于参数解析
        // (方案考虑: 前端使用 stringify 将参数当作  get 请求的参数上传, 后端再通过 json 解析?)
        Collection<Worker> traces = workerBoardService.getTraces(ParamHelper.toProjects(params));
        return R.success(traces);
    }
}

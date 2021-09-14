package com.mantas.tapd.controller;

import com.mantas.base.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 任务看板
 *
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/tapd/work")
public class WorkBoardController {

    /**
     * 获取开发人员的工作列表
     * 按成员分组, 以指定的迭代范围/时间范围,
     * 将员工负责的: 需求, 任务, 缺陷 以业务价值, 严重程度, 优先级 排序
     *
     * @return
     */
    @GetMapping("/developer/list")
    public R listDeveloperWorks(@RequestParam("group") String group, @RequestParam("iters") List<String> iterationList) {
        //获取需求
        //获取任务
        //获取缺陷
        //按业务价值, 严重程度, 优先级 排序
        return R.success(null);
    }
}

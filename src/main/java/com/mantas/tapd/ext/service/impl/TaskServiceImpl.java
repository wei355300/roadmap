package com.mantas.tapd.ext.service.impl;

import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.TapdResult;
import com.mantas.tapd.TapdTask;
import com.mantas.tapd.TapdTaskResult;
import com.mantas.tapd.TapdURL;
import com.mantas.tapd.ext.dto.Iteration;
import com.mantas.tapd.ext.dto.Task;
import com.mantas.tapd.ext.dto.mapper.TaskConvert;
import com.mantas.tapd.ext.service.TapdRequest;
import com.mantas.tapd.ext.service.TaskService;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TaskServiceImpl implements TaskService {

    private TapdRequest tapdRequest;

    public TaskServiceImpl(TapdRequest tapdRequest) {
        this.tapdRequest = tapdRequest;
    }

    @Override
    public List<List<Task>> getByIterations(Integer projectId, Collection<Iteration> iterations) {
        //需要按照每个迭代获取任务(不支持多迭代一次性获取任务)
        List<List<Task>> tasks = iterations.stream().map(iteration -> {
            List<ParamPair> pairs = tapdRequest.setParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
            tapdRequest.appendParams(pairs, TapdURL.PARAM.ITERATION_ID, iteration.getId());
            tapdRequest.appendParams(pairs, TapdURL.PARAM.LIMIT, "200");

            TapdResult<List<TapdTask>> data = tapdRequest.get(TapdURL.URL.TASKS, pairs, TapdTaskResult.class);
            return convertTask(data);
        }).collect(Collectors.toList());
        return tasks;
    }

    private List<Task> convertTask(TapdResult<List<TapdTask>> data) {
        return data.getData().stream().map(s -> TaskConvert.INSTANCE.toTask(s.getTask())).collect(Collectors.toList());
    }
}

package com.mantas.tapd.service.impl;

import com.jayway.jsonpath.TypeRef;
import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.dto.Iteration;
import com.mantas.tapd.dto.Task;
import com.mantas.tapd.service.TapdClient;
import com.mantas.tapd.service.TapdURL;
import com.mantas.tapd.service.TaskService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TaskServiceImpl implements TaskService {

    private TapdClient tapdClient;

    public TaskServiceImpl(TapdClient tapdClient) {
        this.tapdClient = tapdClient;
    }

    @Override
    public List<List<Task>> getByIterations(Integer projectId, Collection<Iteration> iterations) {
        //需要按照每个迭代获取任务(不支持多迭代一次性获取任务)
        List<List<Task>> tasks = iterations.stream().map(iteration -> request(projectId, iteration)).collect(Collectors.toList());
        return tasks;
    }

    @Override
    public List<Task> getByIterations(Integer projectId, Iteration iteration) {
        return request(projectId, iteration);
    }

    private List<Task> request(Integer projectId, Iteration iteration) {
        return request(projectId, iteration.getId());
    }

    private List<Task> request(Integer projectId, String iterationId) {
        List<ParamPair> pairs = tapdClient.setParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
        tapdClient.appendParams(pairs, TapdURL.PARAM.ITERATION_ID, iterationId);
        tapdClient.appendParams(pairs, TapdURL.PARAM.LIMIT, "200");

        try {
            return tapdClient.get(TapdURL.URL.TASKS, pairs, new TypeRef<List<Task>>() {}, "$.data[*].Task");
        } catch (IOException e) {
            log.warn("request err: \n {}", e);
        }
        return Collections.EMPTY_LIST;
    }
}

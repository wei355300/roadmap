package com.mantas.tapd.task;

import com.jayway.jsonpath.TypeRef;
import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.iteration.Iteration;
import com.mantas.tapd.TapdClient;
import com.mantas.tapd.TapdURL;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TaskService {

    private TapdClient tapdClient;

    public TaskService(TapdClient tapdClient) {
        this.tapdClient = tapdClient;
    }

    public List<List<Task>> getByIterations(Integer projectId, Collection<Iteration> iterations) {
        //需要按照每个迭代获取任务(不支持多迭代一次性获取任务)
        List<List<Task>> tasks = iterations.stream().map(iteration -> request(projectId, iteration)).collect(Collectors.toList());
        return tasks;
    }

    public List<Task> getByIterations(Integer projectId, Iteration iteration) {
        return request(projectId, iteration);
    }

    private List<Task> request(Integer projectId, Iteration iteration) {
        return request(projectId, iteration.getId());
    }

    private List<Task> request(Integer projectId, String iterationId) {
        List<ParamPair> pairs = tapdClient.buildParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
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

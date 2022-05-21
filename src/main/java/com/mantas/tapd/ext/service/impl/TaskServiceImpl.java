package com.mantas.tapd.ext.service.impl;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.ext.dto.Iteration;
import com.mantas.tapd.ext.dto.Story;
import com.mantas.tapd.ext.dto.Task;
import com.mantas.tapd.ext.service.TaskService;
import com.mantas.tapd.origin.TapdClient;
import com.mantas.tapd.origin.TapdURL;
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

    private List<Task> request(Integer projectId, Iteration iteration) {
        return request(projectId, iteration.getId());
    }

    private List<Task> request(Integer projectId, String iterationId) {
        List<ParamPair> pairs = tapdClient.setParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
        tapdClient.appendParams(pairs, TapdURL.PARAM.ITERATION_ID, iterationId);
        tapdClient.appendParams(pairs, TapdURL.PARAM.LIMIT, "200");

        try {
            String body = tapdClient.get(TapdURL.URL.TASKS, pairs);
            return body2Task(body);
        } catch (IOException e) {
            log.warn("request err: \n {}", e);
        }
        return Collections.EMPTY_LIST;
    }

    private List<Task> body2Task(String body) {
        log.debug("response task body:\n {}", body);
        TypeRef<List<Task>> typeRef = new TypeRef<List<Task>>() {};
        List<Task> tasks = JsonPath.parse(body).read("$.data.*", typeRef);
        log.debug("parsed tasks result: \n {}", tasks);
        return tasks;
    }
}

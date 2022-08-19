package com.mantas.tapd.service;

import com.jayway.jsonpath.TypeRef;
import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.dto.Bug;
import com.mantas.tapd.dto.Iteration;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BugService {

    private TapdClient tapdClient;

    public BugService(TapdClient tapdClient) {
        this.tapdClient = tapdClient;
    }

    public List<List<Bug>> getByIterations(Integer projectId, Collection<Iteration> iterations) {
        //需要按照每个迭代获取任务(不支持多迭代一次性获取任务)
        List<List<Bug>> bugs = iterations.stream().map(iteration -> request(projectId, iteration.getId())).collect(Collectors.toList());
        return bugs;
    }

    public List<Bug> getByIteration(Integer projectId, String iterationId) {
        return request(projectId, iterationId);
    }

    private List<Bug> request(Integer projectId, String iterationId) {
        List<ParamPair> pairs = tapdClient.setParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
        tapdClient.appendParams(pairs, TapdURL.PARAM.ITERATION_ID, iterationId);
        tapdClient.appendParams(pairs, TapdURL.PARAM.LIMIT, "200");

        try {
            return tapdClient.get(TapdURL.URL.BUGS, pairs, new TypeRef<List<Bug>>() {}, "$.data[*].Bug");
        } catch (IOException e) {
            log.warn("request err: \n {}", e);
        }
        return Collections.EMPTY_LIST;
    }
}

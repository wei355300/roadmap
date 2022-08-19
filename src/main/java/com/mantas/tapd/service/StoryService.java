package com.mantas.tapd.service;

import com.jayway.jsonpath.TypeRef;
import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.dto.Iteration;
import com.mantas.tapd.dto.Story;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class StoryService {

    private TapdClient tapdClient;

    public StoryService(TapdClient tapdClient) {
        this.tapdClient = tapdClient;
    }

    public List<List<Story>> getByIterations(Integer projectId, Collection<Iteration> iterations) {
        //需要按照每个迭代获取需求(不支持多迭代一次性获取需求)
        List<List<Story>> stories = iterations.stream().map(iteration -> request(projectId, iteration)).collect(Collectors.toList());
        return stories;
    }

    public List<Story> getByIteration(Integer projectId, String iterationId) {
        //需要按照每个迭代获取需求(不支持多迭代一次性获取需求)
        List<Story> stories = request(projectId, iterationId);
        return stories;
    }

    private List<Story> request(Integer projectId, Iteration iteration) {
        return request(projectId, iteration.getId());
    }

    private List<Story> request(Integer projectId, String iterationId) {
        List<ParamPair> pairs = tapdClient.setParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
        tapdClient.appendParams(pairs, TapdURL.PARAM.ITERATION_ID, iterationId);
        tapdClient.appendParams(pairs, TapdURL.PARAM.LIMIT, "200");

        try {
            return tapdClient.get(TapdURL.URL.STORIES, pairs, new TypeRef<List<Story>>() {}, "$.data[*].Story");
        } catch (IOException e) {
            log.warn("request err: \n {}", e);
        }
        return Collections.EMPTY_LIST;
    }
}

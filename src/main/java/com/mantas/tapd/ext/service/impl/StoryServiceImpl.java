package com.mantas.tapd.ext.service.impl;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.ext.dto.Iteration;
import com.mantas.tapd.ext.dto.Story;
import com.mantas.tapd.ext.service.StoryService;
import com.mantas.tapd.origin.TapdClient;
import com.mantas.tapd.origin.TapdURL;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class StoryServiceImpl implements StoryService {

    private TapdClient tapdClient;

    public StoryServiceImpl(TapdClient tapdClient) {
        this.tapdClient = tapdClient;
    }

    @Override
    public List<List<Story>> getByIterations(Integer projectId, Collection<Iteration> iterations) {
        //需要按照每个迭代获取需求(不支持多迭代一次性获取需求)
        List<List<Story>> stories = iterations.stream().map(iteration -> request(projectId, iteration)).collect(Collectors.toList());
        return stories;
    }

    @Override
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
            String body = tapdClient.get(TapdURL.URL.STORIES, pairs);
            return body2Story(body);
        } catch (IOException e) {
            log.warn("request err: \n {}", e);
        }
        return Collections.EMPTY_LIST;
    }

    private List<Story> body2Story(String body) {
        log.debug("response story body:\n {}", body);
        TypeRef<List<Story>> typeRef = new TypeRef<List<Story>>() {};
        List<Story> stories = JsonPath.parse(body).read("$.data[*].Story", typeRef);
        log.debug("parsed story result: \n {}", stories);
        return stories;
    }
}

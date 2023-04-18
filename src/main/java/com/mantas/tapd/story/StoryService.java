package com.mantas.tapd.story;

import com.jayway.jsonpath.TypeRef;
import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.iteration.Iteration;
import com.mantas.tapd.TapdClient;
import com.mantas.tapd.TapdURL;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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

    public List<Story> getByDate(Integer projectId, String start, String end, String[] status) {
        List<Story> stories = request(projectId, start, end, status);
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

    private List<Story> request(Integer projectId, String start, String due, String[] status) {
        List<ParamPair> pairs = tapdClient.buildParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
        if (Objects.nonNull(status)) {
            tapdClient.appendParams(pairs, TapdURL.PARAM.STORY.BEGIN, ">"+start);
        }
        if (Objects.nonNull(due)) {
            tapdClient.appendParams(pairs, TapdURL.PARAM.STORY.DUE, "<"+due);
        }
        if (Objects.nonNull(status)) {
            String s = StringUtils.join(status, "|");
            tapdClient.appendParams(pairs, TapdURL.PARAM.STORY.STATUS, s);
        }
        tapdClient.appendParams(pairs, TapdURL.PARAM.LIMIT, "100");

        try {
            return tapdClient.get(TapdURL.URL.STORIES, pairs, new TypeRef<List<Story>>() {}, "$.data[*].Story");
        } catch (IOException e) {
            log.warn("request err: \n {}", e);
        }
        return Collections.EMPTY_LIST;
    }

    private List<Story> request(Integer projectId, String iterationId) {
        List<ParamPair> pairs = tapdClient.buildParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
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

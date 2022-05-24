package com.mantas.tapd.ext.service.impl;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.ext.dto.Bug;
import com.mantas.tapd.ext.dto.Iteration;
import com.mantas.tapd.ext.service.BugService;
import com.mantas.tapd.origin.TapdClient;
import com.mantas.tapd.origin.TapdURL;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BugServiceImpl implements BugService {

    private TapdClient tapdClient;

    public BugServiceImpl(TapdClient tapdClient) {
        this.tapdClient = tapdClient;
    }

    @Override
    public List<List<Bug>> getByIterations(Integer projectId, Collection<Iteration> iterations) {
        //需要按照每个迭代获取任务(不支持多迭代一次性获取任务)
        List<List<Bug>> bugs = iterations.stream().map(iteration -> request(projectId, iteration.getId())).collect(Collectors.toList());
        return bugs;
    }

    @Override
    public List<Bug> getByIteration(Integer projectId, String iterationId) {
        return request(projectId, iterationId);
    }

    private List<Bug> request(Integer projectId, String iterationId) {
        List<ParamPair> pairs = tapdClient.setParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
        tapdClient.appendParams(pairs, TapdURL.PARAM.ITERATION_ID, iterationId);
        tapdClient.appendParams(pairs, TapdURL.PARAM.LIMIT, "200");

        try {
            String body = tapdClient.get(TapdURL.URL.BUGS, pairs);
            return body2Bug(body);
        } catch (IOException e) {
            log.warn("request err: \n {}", e);
        }
        return Collections.EMPTY_LIST;
    }

    private List<Bug> body2Bug(String body) {
        log.debug("response bugs body:\n {}", body);
        TypeRef<List<Bug>> typeRef = new TypeRef<List<Bug>>() {};
        List<Bug> bugs = JsonPath.parse(body).read("$.data[*].Bug", typeRef);
        log.debug("parsed bugs result: \n {}", bugs);
        return bugs;
    }
}

package com.mantas.tapd.service.impl;

import com.jayway.jsonpath.TypeRef;
import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.dto.Iteration;
import com.mantas.tapd.service.IterationService;
import com.mantas.tapd.service.TapdClient;
import com.mantas.tapd.service.TapdURL;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
public class IterationServiceImpl implements IterationService {

    private TapdClient tapdClient;

    public IterationServiceImpl(TapdClient tapdClient) {
        this.tapdClient = tapdClient;
    }

    @Override
    public List<Iteration> list(Date startDate) {
        return null;
    }

    @Override
    public List<Iteration> getIterationsByProject(Integer projectId) {
        return request(projectId);
    }

    private List<Iteration> request(Integer projectId) {
        List<ParamPair> pairs = tapdClient.setParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
        tapdClient.appendParams(pairs, TapdURL.PARAM.STATUS, "open");

        try {
            return tapdClient.get(TapdURL.URL.ITERATIONS, pairs, new TypeRef<List<Iteration>>() {}, "$.data[*].Iteration");
        } catch (IOException e) {
            log.warn("request err: \n {}", e);
        }
        return Collections.EMPTY_LIST;
    }
}

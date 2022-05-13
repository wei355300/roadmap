package com.mantas.tapd.ext.service.impl;

import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.TapdIteration;
import com.mantas.tapd.TapdIterationResult;
import com.mantas.tapd.TapdResult;
import com.mantas.tapd.TapdURL;
import com.mantas.tapd.ext.dto.Iteration;
import com.mantas.tapd.ext.dto.mapper.IterationConvert;
import com.mantas.tapd.ext.service.IterationService;
import com.mantas.tapd.ext.service.TapdRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
public class IterationServiceImpl implements IterationService {

    private TapdRequest tapdRequest;

    public IterationServiceImpl(TapdRequest tapdRequest) {
        this.tapdRequest = tapdRequest;
    }

    @Override
    public List<Iteration> list(Date startDate) {
        return null;
    }

    @Override
    public List<Iteration> getIterationsByProject(Integer projectId) {

        List<ParamPair> pairs = tapdRequest.setParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
        tapdRequest.appendParams(pairs, TapdURL.PARAM.STATUS, "open");

        TapdResult<List<TapdIteration>> data = tapdRequest.get(TapdURL.URL.ITERATIONS, pairs, TapdIterationResult.class);
        return convert(data, IterationConvert.INSTANCE);
    }

    protected List<Iteration> convert(TapdResult<List<TapdIteration>> data, IterationConvert mapper) {
        if (Objects.nonNull(data) && Objects.nonNull(data.getData())) {
            return mapper.toIterations(data.getData());
        }
        return Collections.emptyList();
    }
}

package com.mantas.tapd.ext.service.impl;

import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.TapdBug;
import com.mantas.tapd.TapdBugResult;
import com.mantas.tapd.TapdResult;
import com.mantas.tapd.TapdURL;
import com.mantas.tapd.ext.dto.Bug;
import com.mantas.tapd.ext.dto.Iteration;
import com.mantas.tapd.ext.dto.mapper.BugConvert;
import com.mantas.tapd.ext.service.BugService;
import com.mantas.tapd.ext.service.TapdRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BugServiceImpl implements BugService {

    private TapdRequest tapdRequest;

    @Autowired
    public BugServiceImpl(TapdRequest tapdRequest) {
        this.tapdRequest = tapdRequest;
    }

    @Override
    public List<List<Bug>> getByIterations(Integer projectId, Collection<Iteration> iterations) {
        //需要按照每个迭代获取任务(不支持多迭代一次性获取任务)
        List<List<Bug>> bugs = iterations.stream().map(iteration -> {
            List<ParamPair> pairs = tapdRequest.setParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
            tapdRequest.appendParams(pairs, TapdURL.PARAM.ITERATION_ID, iteration.getId());
            tapdRequest.appendParams(pairs, TapdURL.PARAM.LIMIT, "200");

            TapdResult<List<TapdBug>> data = tapdRequest.get(TapdURL.URL.BUGS, pairs, TapdBugResult.class);
            return convertBug(data);
        }).collect(Collectors.toList());
        return bugs;
    }

    private List<Bug> convertBug(TapdResult<List<TapdBug>> data) {
        return data.getData().stream().map(s -> BugConvert.INSTANCE.toBug(s.getBug())).collect(Collectors.toList());
    }
}

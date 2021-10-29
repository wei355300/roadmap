package com.mantas.tapd.ext.service.impl;

import com.mantas.tapd.ext.conf.TapdURL;
import com.mantas.tapd.ext.dto.Story;
import com.mantas.tapd.ext.dto.mapper.StructMapper;
import com.mantas.tapd.ext.dto.mapper.TapdStoryMapper;
import com.mantas.tapd.ext.dto.tapd.TapdData;
import com.mantas.tapd.ext.dto.tapd.TapdDataIt;
import com.mantas.tapd.ext.dto.tapd.TapdStoryData;
import com.mantas.tapd.ext.service.StoryService;
import com.mantas.okhttp.OkHttp;
import com.mantas.tapd.ext.service.TapdRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StoryServiceImpl implements StoryService {

    private TapdRequest tapdRequest;

    @Autowired
    public StoryServiceImpl(TapdRequest tapdRequest) {
        this.tapdRequest = tapdRequest;
    }

    @Override
    public List<Story> listByIteration(String iterationId) {
        TapdStoryData data = tapdRequest.get(TapdURL.URL.STORIES, tapdRequest.setParam(TapdURL.PARAM.ITERATION_ID, iterationId), TapdStoryData.class);
        return convert(data, TapdStoryMapper.mapper);
    }

    @Override
    public List<Story> listByRelease(String releaseId) {
        TapdStoryData data = tapdRequest.get(TapdURL.URL.STORIES, tapdRequest.setParam(TapdURL.PARAM.RELEASE_ID, releaseId), TapdStoryData.class);
        return convert(data, TapdStoryMapper.mapper);
    }

    protected  <K extends TapdDataIt> List convert(TapdData<K> data, StructMapper mapper) {
        if (Objects.nonNull(data) && Objects.nonNull(data.getData())) {
            return data.getData().stream().map(m -> mapper.mapper(m.getEntity())).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}

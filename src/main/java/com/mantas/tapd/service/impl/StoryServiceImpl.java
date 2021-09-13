package com.mantas.tapd.service.impl;

import com.mantas.tapd.conf.TapdURL;
import com.mantas.tapd.dto.Story;
import com.mantas.tapd.dto.mapper.StructMapper;
import com.mantas.tapd.dto.mapper.TapdStoryMapper;
import com.mantas.tapd.dto.tapd.TapdData;
import com.mantas.tapd.dto.tapd.TapdDataIt;
import com.mantas.tapd.dto.tapd.TapdStoryData;
import com.mantas.tapd.service.StoryService;
import com.mantas.connector.OkHttp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StoryServiceImpl extends TapdBuiler implements StoryService {

    public StoryServiceImpl(@Autowired OkHttp okHttp) {
        super(okHttp);
    }

    @Override
    public List<Story> listByIteration(String iterationId) {
        TapdStoryData data = get(TapdURL.TAPD_URL_STORIES, setParam(TapdURL.TAPD_PARAM_ITERATION_ID, iterationId), TapdStoryData.class);
        return convert(data, TapdStoryMapper.mapper);
    }

    @Override
    public List<Story> listByRelease(String releaseId) {
        TapdStoryData data = get(TapdURL.TAPD_URL_STORIES, setParam(TapdURL.TAPD_PARAM_RELEASE_ID, releaseId), TapdStoryData.class);
        return convert(data, TapdStoryMapper.mapper);
    }

    protected  <K extends TapdDataIt> List convert(TapdData<K> data, StructMapper mapper) {
        if (Objects.nonNull(data) && Objects.nonNull(data.getData())) {
            return data.getData().stream().map(m -> mapper.mapper(m.getEntity())).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}

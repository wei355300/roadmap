package com.mantas.tapd.service.impl;

import com.mantas.tapd.conf.TapdConf;
import com.mantas.tapd.dto.Story;
import com.mantas.tapd.dto.mapper.TapdStoryMapper;
import com.mantas.tapd.dto.tapd.TapdStoryData;
import com.mantas.tapd.service.StoryService;
import com.mantas.tapd.connector.OkHttp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StoryServiceImpl extends TapdBuiler implements StoryService {

    public StoryServiceImpl(@Autowired OkHttp okHttp) {
        super(okHttp);
    }

    @Override
    public List<Story> listByIteration(String iterationId) {
        TapdStoryData data = get(TapdConf.TAPD.TAPD_URL_STORIES, setParam(TapdConf.TAPD.TAPD_PARAM_ITERATION_ID, iterationId), TapdStoryData.class);
        return convert(data, TapdStoryMapper.mapper);
    }

    @Override
    public List<Story> listByRelease(String releaseId) {
        TapdStoryData data = get(TapdConf.TAPD.TAPD_URL_STORIES, setParam(TapdConf.TAPD.TAPD_PARAM_RELEASE_ID, releaseId), TapdStoryData.class);
        return convert(data, TapdStoryMapper.mapper);
    }
}

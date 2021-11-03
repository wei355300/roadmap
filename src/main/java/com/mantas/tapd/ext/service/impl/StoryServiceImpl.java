package com.mantas.tapd.ext.service.impl;

import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.TapdResult;
import com.mantas.tapd.TapdStoryResult;
import com.mantas.tapd.TapdURL;
import com.mantas.tapd.ext.dto.Iteration;
import com.mantas.tapd.ext.dto.Story;
import com.mantas.tapd.ext.dto.mapper.StoryConvert;
import com.mantas.tapd.ext.dto.tapd.TapdStory;
import com.mantas.tapd.ext.service.StoryService;
import com.mantas.tapd.ext.service.TapdRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StoryServiceImpl implements StoryService {

    private TapdRequest tapdRequest;

    @Autowired
    public StoryServiceImpl(TapdRequest tapdRequest) {
        this.tapdRequest = tapdRequest;
    }

//    @Override
//    public List<Story> listByIteration(String iterationId) {
//        TapdStoryData data = tapdRequest.get(TapdURL.URL.STORIES, tapdRequest.setParam(TapdURL.PARAM.ITERATION_ID, iterationId), TapdStoryData.class);
//        return convert(data, TapdStoryMapper.mapper);
//    }
//
//    @Override
//    public List<Story> listByRelease(String releaseId) {
//        TapdStoryData data = tapdRequest.get(TapdURL.URL.STORIES, tapdRequest.setParam(TapdURL.PARAM.RELEASE_ID, releaseId), TapdStoryData.class);
//        return convert(data, TapdStoryMapper.mapper);
//    }

    @Override
    public List<List<Story>> getByIterations(Integer projectId, Collection<Iteration> iterations) {
        //需要按照每个迭代获取需求(不支持多迭代一次性获取需求)
        List<List<Story>> stories = iterations.stream().map(iteration -> {
            List<ParamPair> pairs = tapdRequest.setParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
            tapdRequest.appendParams(pairs, TapdURL.PARAM.ITERATION_ID, iteration.getId());
            tapdRequest.appendParams(pairs, TapdURL.PARAM.LIMIT, "200");

            TapdResult<List<TapdStory>> data = tapdRequest.get(TapdURL.URL.STORIES, pairs, TapdStoryResult.class);
            return convertStory(data);
        }).collect(Collectors.toList());
        return stories;
    }

    /**
     * 按业务价值排序
     * @param data
     * @return
     */
    private List<Story> convertStory(TapdResult<List<TapdStory>> data) {
        return data.getData().stream().map(s -> StoryConvert.INSTANCE.toStory(s.getStory())).sorted((s1, s2)->{
            return s1.getBusinessValue() - s2.getBusinessValue();
        }).collect(Collectors.toList());
    }

//    protected  <K extends TapdDataIt> List convert(TapdData<K> data, StructMapper mapper) {
//        if (Objects.nonNull(data) && Objects.nonNull(data.getData())) {
//            return data.getData().stream().map(m -> mapper.mapper(m.getEntity())).collect(Collectors.toList());
//        }
//        return Collections.emptyList();
//    }
}

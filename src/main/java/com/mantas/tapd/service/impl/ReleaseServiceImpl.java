package com.mantas.tapd.service.impl;

import com.mantas.tapd.conf.TapdConf;
import com.mantas.tapd.conf.TapdURL;
import com.mantas.tapd.dto.Release;
import com.mantas.tapd.dto.mapper.StructMapper;
import com.mantas.tapd.dto.mapper.TapdReleaseMapper;
import com.mantas.tapd.dto.tapd.TapdData;
import com.mantas.tapd.dto.tapd.TapdDataIt;
import com.mantas.tapd.dto.tapd.TapdReleaseData;
import com.mantas.tapd.service.ReleaseService;
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
public class ReleaseServiceImpl extends TapdBuiler implements ReleaseService {

    protected ReleaseServiceImpl(@Autowired OkHttp okHttp) {
        super(okHttp);
    }

    @Override
    public List<Release> list(String day) {
        TapdReleaseData data = get(TapdURL.TAPD_URL_RELEASE, setParam(TapdURL.TAPD_PARAM_START_DATE, day), TapdReleaseData.class);
        return convert(data, TapdReleaseMapper.mapper);
    }

    protected  <K extends TapdDataIt> List convert(TapdData<K> data, StructMapper mapper) {
        if (Objects.nonNull(data) && Objects.nonNull(data.getData())) {
            return data.getData().stream().map(m -> mapper.mapper(m.getEntity())).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}

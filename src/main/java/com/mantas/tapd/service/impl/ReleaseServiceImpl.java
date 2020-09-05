package com.mantas.tapd.service.impl;

import com.mantas.tapd.conf.TapdConf;
import com.mantas.tapd.dto.Release;
import com.mantas.tapd.dto.mapper.TapdReleaseMapper;
import com.mantas.tapd.dto.tapd.TapdReleaseData;
import com.mantas.tapd.service.ReleaseService;
import com.mantas.tapd.connector.OkHttp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReleaseServiceImpl extends TapdBuiler implements ReleaseService {

    protected ReleaseServiceImpl(@Autowired OkHttp okHttp) {
        super(okHttp);
    }

    @Override
    public List<Release> list(String day) {
        TapdReleaseData data = get(TapdConf.TAPD.TAPD_URL_RELEASE, setParam(TapdConf.TAPD.TAPD_PARAM_START_DATE, day), TapdReleaseData.class);
        return convert(data, TapdReleaseMapper.mapper);
    }
}

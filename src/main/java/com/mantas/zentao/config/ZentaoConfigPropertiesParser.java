package com.mantas.zentao.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mantas.nacos.NacosConfigurationParser;
import com.mantas.utils.JsonUtils;

public class ZentaoConfigPropertiesParser implements NacosConfigurationParser<ZentaoConfigProperties> {

    @Override
    public ZentaoConfigProperties parse(String config) throws JsonProcessingException {
        ZentaoConfigProperties props = JsonUtils.toObj(config, ZentaoConfigProperties.class);
        return props;
    }
}
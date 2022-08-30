package com.mantas.tapd.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mantas.nacos.NacosConfigurationParser;
import com.mantas.utils.JsonUtils;

public class TapdConfigPropertiesParser implements NacosConfigurationParser<TapdConfigProperties> {

    @Override
    public TapdConfigProperties parse(String config) throws JsonProcessingException {
        TapdConfigProperties props = JsonUtils.toObj(config, TapdConfigProperties.class);
        return props;
    }
}
package com.mantas.nacos;

import com.mantas.utils.JsonUtils;

public class NacosConfigurationJsonParser<T> implements NacosConfigurationParser<T> {

    private Class<T> clazz;

    public NacosConfigurationJsonParser(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T parse(String config) throws Exception {
        return JsonUtils.toObj(config, clazz);
    }
}

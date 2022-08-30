package com.mantas.nacos;

import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.Executor;

@Slf4j
public abstract class NacosConfigurationListener<T> implements Listener {

    private NacosConfigurationParser<T> configParser;

    public NacosConfigurationListener(NacosConfigurationParser<T> configParser) {
        this.configParser = configParser;
    }

    @Override
    public Executor getExecutor() {
        return null;
    }

    @Override
    public void receiveConfigInfo(String configInfo) {
        T t = changed(configInfo);
        if (Objects.isNull(t)) {
            return;
        }
        onUpdated(t);
    }

    protected abstract void onUpdated(T t);

    private T changed(String content) {
        T t = null;
        try {
            t = configParser.parse(content);
        } catch (Exception e) {
            log.warn("can not parse changed configuration", e);
        }
        return t;
    }


}

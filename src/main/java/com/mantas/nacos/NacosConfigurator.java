package com.mantas.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * nacos配置操作类
 * <p>
 * 主要负责检查配置及操作配置项(增加配置, 修改配置, 删除配置)
 */
@Slf4j
public class NacosConfigurator implements BeanPostProcessor {

    private final static Map<String, ConfigService> services = new HashMap<>();

    public static <T> T getConfig(NacosConf nacosConf, Type type) throws NacosException {
        ConfigService configService = getConfigService(nacosConf);

        String content = configService.getConfig(nacosConf.getDataId(), nacosConf.getGroupId(), 5000);

        log.info("get content {} from nacos server by conf {}", content, nacosConf);

        T result = new Gson().fromJson(content, type);
        return result;
    }

    public static <T> boolean publishConfig(NacosConf nacosConf, T content) throws NacosException {
        ConfigService configService = getConfigService(nacosConf);
        return configService.publishConfig(nacosConf.getDataId(), nacosConf.getGroupId(), new Gson().toJson(content));
    }

    private static ConfigService getConfigService(NacosConf nacosConf) throws NacosException {
        ConfigService configService = services.get(nacosConf.getModule());
        if (Objects.isNull(configService)) {
            Properties props = new Properties();
            props.put(PropertyKeyConst.SERVER_ADDR, nacosConf.getServerAddr());
            props.put(PropertyKeyConst.NAMESPACE, nacosConf.getNamespace());
            configService = NacosFactory.createConfigService(props);
            services.put(nacosConf.getModule(), configService);
        }
        return configService;
    }
}

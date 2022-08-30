package com.mantas.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mantas.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * nacos配置操作类
 * <p>
 * 主要负责检查配置及操作配置项(增加配置, 修改配置, 删除配置)
 *
 *
 * 注意:
 *  本实现 直接使用的 nacos-sdk,
 *  没有使用 nacos-spring | nacos-spring-boot | nacos-spring-cloud 的实现
 *
 * 参考: (Java-SDK) https://nacos.io/zh-cn/docs/sdk.html
 */
@Slf4j
public class NacosConfigurator {

    private final static Map<String, ConfigService> services = new HashMap<>();

    public static <T> T getConfig(NacosProperties nacosConf, Class<T> type) throws NacosException, JsonProcessingException {
        ConfigService configService = getConfigService(nacosConf);
        String content = configService.getConfig(nacosConf.getDataId(), nacosConf.getGroupId(), 5000);
        T result = JsonUtils.toObj(content, type);
        return result;
    }

    public static <T> T getConfig(NacosProperties nacosProperties, NacosConfigurationParser<T> parser) throws Exception {
        ConfigService configService = getConfigService(nacosProperties);
        String content = configService.getConfig(nacosProperties.getDataId(), nacosProperties.getGroupId(), 5000);
        return parser.parse(content);
    }

//    public static <T> T getConfig(NacosProperties nacosProperties, NacosConfigurationParser parser, NacosConfigurationListener listener) throws Exception {
//        ConfigService configService = getConfigService(nacosProperties);
//        String content = configService.getConfigAndSignListener(nacosProperties.getDataId(), nacosProperties.getGroupId(), 5000, listener);
//        return parser.parse(content);
//    }

    public static void removeListener(NacosProperties nacosProperties, NacosConfigurationListener listener) throws NacosException {
        ConfigService configService = getConfigService(nacosProperties);
        configService.removeListener(nacosProperties.getDataId(), nacosProperties.getGroupId(), listener);
    }

    private static ConfigService getConfigService(NacosProperties nacosProperties) throws NacosException {
        ConfigService configService = services.get(nacosProperties.getModule());
        if (Objects.isNull(configService)) {
            Properties props = new Properties();
            props.put(PropertyKeyConst.SERVER_ADDR, nacosProperties.getServerAddr());
            props.put(PropertyKeyConst.NAMESPACE, nacosProperties.getNamespace());
            configService = NacosFactory.createConfigService(props);
            services.put(nacosProperties.getModule(), configService);
        }
        return configService;
    }
}

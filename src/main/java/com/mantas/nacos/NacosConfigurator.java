package com.mantas.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mantas.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.Executor;

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

    /**
     * fixme : 使用@Bean注入的方式
     * json解析可利用spring的ObjectMapper处理
     * @Bean
     * public NacosConfigurator nacosConfigurator(ObjectMapper objectMapper) {}
     */

    public class Listener implements com.alibaba.nacos.api.config.listener.Listener {
        @Override
        public Executor getExecutor() {
            return null;
        }

        @Override
        public void receiveConfigInfo(String configInfo) {

        }
    }

    private final static Map<String, ConfigService> services = new HashMap<>();

    public static <T> T getConfig(NacosConf nacosConf, Class<T> type) throws NacosException, JsonProcessingException {
        ConfigService configService = getConfigService(nacosConf);

        // 可以增加配置修改后的listener
//        String content = configService.getConfigAndSignListener(nacosConf.getDataId(), nacosConf.getGroupId(), 5000, null);
        String content = configService.getConfig(nacosConf.getDataId(), nacosConf.getGroupId(), 5000);

        log.info("get content {} from nacos server by conf {}", content, nacosConf);

        T result = JsonUtils.toObj(content, type);
        return result;
    }

    public static <T> T getConfig(NacosConf nacosConf, Class<T> type, Listener listener) throws NacosException, JsonProcessingException {
        ConfigService configService = getConfigService(nacosConf);
        String content = configService.getConfigAndSignListener(nacosConf.getDataId(), nacosConf.getGroupId(), 5000, listener);

        log.info("get content {} from nacos server by conf {}", content, nacosConf);
        T result = JsonUtils.toObj(content, type);
        return result;
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

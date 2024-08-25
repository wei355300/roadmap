package com.mantas.zentao.config;

import com.mantas.nacos.NacosConfigurationJsonParser;
import com.mantas.nacos.NacosConfigurator;
import com.mantas.tapd.service.*;
import com.mantas.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ZentaoConfiguration {

    @Bean
    public ZentaoConfigProperties zentaoConfigProperties(@Autowired NacosZentaoProperties nacosZentaoConf) throws Exception {
        ZentaoConfigProperties properties = NacosConfigurator.getConfig(nacosZentaoConf, new NacosConfigurationJsonParser<>(ZentaoConfigProperties.class));
        log.info("zentao properties: {}", JsonUtils.toPrettyJson(properties));
        return properties;
    }
}

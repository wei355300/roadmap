package com.mantas.alilog.config;

import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mantas.alilog.dto.LogEntity;
import com.mantas.alilog.service.AlilogService;
import com.mantas.alilog.service.impl.AlilogClients;
import com.mantas.alilog.service.impl.AlilogServiceImpl;
import com.mantas.gitlab.service.GitFileService;
import com.mantas.nacos.NacosConfigurator;
import org.gitlab4j.api.GitLabApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AlilogConfigurations {

    @Bean
    public AlilogConfigProperties alilogConfigProperties(@Autowired NacosAlilogConf nacosAlilogConf) throws NacosException, JsonProcessingException {
        return NacosConfigurator.getConfig(nacosAlilogConf, AlilogConfigProperties.class);
    }

    @Bean
    public AlilogClients alilogClients(@Autowired AlilogConfigProperties alilogConfigProperties) throws Exception {
        List<LogEntity> logEntities = new AlilogConfigPropertiesParser().parse(alilogConfigProperties);
        return new AlilogClients(logEntities);
    }

    @Bean
    public AlilogService alilogService(@Autowired AlilogClients alilogClients) {
        return new AlilogServiceImpl(alilogClients);
    }
}

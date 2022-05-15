package com.mantas.alilog.config;

import com.alibaba.nacos.api.exception.NacosException;
import com.mantas.alilog.service.AlilogService;
import com.mantas.alilog.service.impl.AlilogServiceImpl;
import com.mantas.gitlab.service.GitFileService;
import com.mantas.nacos.NacosConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlilogConfigurations {

    @Bean
    public AlilogConfigProperties alilogConfigProperties(@Autowired NacosAlilogConf nacosAlilogConf) throws NacosException {
        return NacosConfigurator.getConfig(nacosAlilogConf, AlilogConfigProperties.class);
    }

    @Bean
    public AlilogService alilogService(@Autowired AlilogConfigProperties alilogConfigProperties,
                                       @Autowired GitFileService gitFileService) {
        return new AlilogServiceImpl(alilogConfigProperties.getEntities(), gitFileService);
    }
}

package com.mantas.gitlab.config;

import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mantas.gitlab.GitlabUtils;
import com.mantas.gitlab.service.GitFileService;
import com.mantas.gitlab.service.impl.GitFileServiceImpl;
import com.mantas.nacos.NacosConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GitlabConfigurations {

    @Bean
    public GitlabConfigProperties gitlabConfigProperties(@Autowired NacosGitlabProperties nacosGitlabConf) throws NacosException, JsonProcessingException {
        return NacosConfigurator.getConfig(nacosGitlabConf, GitlabConfigProperties.class);
    }

    @Bean
    public GitlabUtils gitlabUtils(@Autowired GitlabConfigProperties gitlabConfigProperties) throws JsonProcessingException {
        return new GitlabUtils(gitlabConfigProperties);
    }

    @Bean
    public GitFileService gitFileService(@Autowired GitlabUtils gitlabUtils) {
        return new GitFileServiceImpl(gitlabUtils);
    }
}

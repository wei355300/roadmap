package com.mantas.gitlab.config;

import com.alibaba.nacos.api.exception.NacosException;
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
    public NacosGitlabConf nacosGitlabConf() {
        return new NacosGitlabConf();
    }

    @Bean
    public GitlabConfigProperties gitlabConfigProperties(@Autowired NacosGitlabConf nacosGitlabConf) throws NacosException {
        return NacosConfigurator.getConfig(nacosGitlabConf, GitlabConfigProperties.class);
    }

    @Bean
    public GitlabUtils gitlabUtils(@Autowired GitlabConfigProperties gitlabConfigProperties) {
        return new GitlabUtils(gitlabConfigProperties);
    }

    @Bean
    public GitFileService gitFileService(@Autowired GitlabUtils gitlabUtils) {
        return new GitFileServiceImpl(gitlabUtils);
    }
}

package com.mantas.gitlab.config;

import com.mantas.gitlab.GitlabUtils;
import com.mantas.gitlab.service.GitFileService;
import com.mantas.gitlab.service.impl.GitFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GitlabConfigurations {

    @Bean
    public GitlabConfigProperties gitlabConfigProperties(){
        return new GitlabConfigProperties();
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

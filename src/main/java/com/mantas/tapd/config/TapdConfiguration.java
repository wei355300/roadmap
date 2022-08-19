package com.mantas.tapd.config;

import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mantas.nacos.NacosConfigurator;
import com.mantas.okhttp.BasicInterceptor;
import com.mantas.okhttp.OkHttp;
import com.mantas.tapd.service.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Configuration
public class TapdConfiguration {

    @Bean
    public TapdClient tapdClient(@Autowired NacosTapdxConf nacosTapdxConf) throws JsonProcessingException, NacosException {
        TapdConfigProperties tapdConfigProperties = NacosConfigurator.getConfig(nacosTapdxConf, TapdConfigProperties.class);
        log.info("nacos server config content: {}, {}", tapdConfigProperties.getAuth().getBasicAuthId(), tapdConfigProperties);
        BasicInterceptor basicInterceptor = new BasicInterceptor(tapdConfigProperties.getAuth().getBasicAuthId(), tapdConfigProperties.getAuth().getBasicAuthPwd());
        Set<Interceptor> interceptors = new HashSet<>(1);
        interceptors.add(basicInterceptor);
        OkHttp okHttp = new OkHttp(interceptors);
        return new TapdClient(okHttp);
    }


    @Bean
    public com.mantas.tapd.service.BugService bugService(@Autowired TapdClient tapdClient) {
        return new BugService(tapdClient);
    }

    @Bean
    public IterationService iterationService(@Autowired TapdClient tapdClient) {
        return new IterationService(tapdClient);
    }

    @Bean
    public ProjectService projectService(@Autowired NacosTapdxConf nacosTapdxConf) {
        return new ProjectService(nacosTapdxConf);
    }

    @Bean
    public RoleService roleService(@Autowired TapdClient tapdClient) {
        return new RoleService(tapdClient);
    }

    @Bean
    public StoryService storyService(@Autowired TapdClient tapdClient) {
        return new StoryService(tapdClient);
    }

    @Bean
    public TaskService taskService(@Autowired TapdClient tapdClient) {
        return new TaskService(tapdClient);
    }

    @Bean
    public WorkerBoardService workerBoardService(@Autowired RoleService roleService,
                                                 @Autowired IterationService iterationService,
                                                 @Autowired ProjectService projectService,
                                                 @Autowired StoryService storyService,
                                                 @Autowired TaskService taskService,
                                                 @Autowired com.mantas.tapd.service.BugService bugService) {
        return new WorkerBoardService(roleService,
                iterationService,
                projectService,
                storyService,
                taskService,
                bugService);
    }
}

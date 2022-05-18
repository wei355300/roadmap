package com.mantas.tapd.ext.config;

import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mantas.nacos.NacosConfigurator;
import com.mantas.okhttp.BasicInterceptor;
import com.mantas.okhttp.OkHttp;
import com.mantas.tapd.ext.service.*;
import com.mantas.tapd.ext.service.impl.*;
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
    public OkHttp okHttp(@Autowired NacosTapdxConf nacosTapdxConf) throws NacosException, JsonProcessingException {
        TapdConf tapdConf = NacosConfigurator.getConfig(nacosTapdxConf, TapdConf.class);
        log.info("nacos server config content: {}, {}", tapdConf.getAuth().getBasicAuthId(), tapdConf);
        BasicInterceptor basicInterceptor = new BasicInterceptor(tapdConf.getAuth().getBasicAuthId(), tapdConf.getAuth().getBasicAuthPwd());
        Set<Interceptor> interceptors = new HashSet<>(1);
        interceptors.add(basicInterceptor);
        OkHttp okHttp = new OkHttp(interceptors);
        return okHttp;
    }

    @Bean
    public TapdRequest tapdRequest(@Autowired OkHttp okHttp) {
        return new TapdRequest(okHttp);
    }


    @Bean
    public BugService bugService(@Autowired TapdRequest tapdRequest) {
        return new BugServiceImpl(tapdRequest);
    }

    @Bean
    public IterationService iterationService(@Autowired TapdRequest tapdRequest) {
        return new IterationServiceImpl(tapdRequest);
    }

    @Bean
    public ProjectService projectService(@Autowired NacosTapdxConf nacosTapdxConf) {
        return new ProjectServiceImpl(nacosTapdxConf);
    }

    @Bean
    public ReleaseService releaseService(@Autowired OkHttp okHttp) {
        return new ReleaseServiceImpl(okHttp);
    }

    @Bean
    public RoleService roleService(@Autowired TapdRequest tapdRequest) {
        return new RoleServiceImpl(tapdRequest);
    }

    @Bean
    public StoryService storyService(@Autowired TapdRequest tapdRequest) {
        return new StoryServiceImpl(tapdRequest);
    }

    @Bean
    public TaskService taskService(@Autowired TapdRequest tapdRequest) {
        return new TaskServiceImpl(tapdRequest);
    }

    @Bean
    public WorkerBoardService workerBoardService(@Autowired RoleService roleService,
                                                 @Autowired IterationService iterationService,
                                                 @Autowired ProjectService projectService,
                                                 @Autowired StoryService storyService,
                                                 @Autowired TaskService taskService,
                                                 @Autowired BugService bugService) {
        return new WorkerBoardServiceImpl(roleService,
                iterationService,
                projectService,
                storyService,
                taskService,
                bugService);
    }
}

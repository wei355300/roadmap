package com.mantas.tapd.config;

import com.mantas.nacos.NacosConfigurationJsonParser;
import com.mantas.nacos.NacosConfigurator;
import com.mantas.okhttp.BasicInterceptor;
import com.mantas.okhttp.OkHttp;
import com.mantas.tapd.TapdClient;
import com.mantas.tapd.bug.BugService;
import com.mantas.tapd.iteration.IterationService;
import com.mantas.tapd.project.ProjectService;
import com.mantas.tapd.service.*;
import com.mantas.tapd.story.StoryService;
import com.mantas.tapd.task.TaskService;
import com.mantas.tapd.user.RoleService;
import com.mantas.tapd.workspace.WorkspaceService;
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
    public TapdClient tapdClient(@Autowired NacosTapdxProperties nacosTapdxConf) throws Exception {
        TapdConfigProperties tapdConfigProperties = NacosConfigurator.getConfig(nacosTapdxConf, new NacosConfigurationJsonParser<>(TapdConfigProperties.class));
        BasicInterceptor basicInterceptor = new BasicInterceptor(tapdConfigProperties.getAuth().getBasicAuthId(), tapdConfigProperties.getAuth().getBasicAuthPwd());
        Set<Interceptor> interceptors = new HashSet<>(1);
        interceptors.add(basicInterceptor);
        OkHttp okHttp = new OkHttp(interceptors);
        return new TapdClient(okHttp);
    }


    @Bean
    public BugService bugService(@Autowired TapdClient tapdClient) {
        return new BugService(tapdClient);
    }

    @Bean
    public IterationService iterationService(@Autowired TapdClient tapdClient) {
        return new IterationService(tapdClient);
    }

    @Bean
    public ProjectService projectService(@Autowired NacosTapdxProperties nacosTapdxConf) {
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

//    @Bean
//    public WorkspaceService workspaceService(@Autowired TapdClient tapdClient) {
//        return new WorkspaceService(tapdClient.get);
//    }

    @Bean
    public WorkerBoardService workerBoardService(@Autowired RoleService roleService,
                                                 @Autowired IterationService iterationService,
                                                 @Autowired ProjectService projectService,
                                                 @Autowired StoryService storyService,
                                                 @Autowired TaskService taskService,
                                                 @Autowired BugService bugService) {
        return new WorkerBoardService(roleService,
                iterationService,
                projectService,
                storyService,
                taskService,
                bugService);
    }
}

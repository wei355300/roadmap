package com.mantas.tapd.project;

import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mantas.nacos.NacosConf;
import com.mantas.nacos.NacosConfigurator;
import com.mantas.tapd.config.NacosTapdxConf;
import com.mantas.tapd.config.TapdConfigProperties;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * tapd 的项目(project)相关
 */
@Slf4j
public class ProjectService {


    private NacosConf nacosTapdxConf;

    public ProjectService(NacosTapdxConf nacosTapdxConf) {
        this.nacosTapdxConf = nacosTapdxConf;
    }

    public List<Project> getProjects() {
        List<Project> projects = null;
        try {
            TapdConfigProperties config = NacosConfigurator.getConfig(nacosTapdxConf, TapdConfigProperties.class);
            projects = config.getProjects();
        } catch (NacosException e) {
            log.warn("get projects from nacos error ", e);
        } catch (JsonProcessingException e) {
            log.warn("parse data error ", e);
        }
        return projects;
    }
}

package com.mantas.tapd.project;

import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mantas.nacos.NacosConfigurationJsonParser;
import com.mantas.nacos.NacosConfigurationParser;
import com.mantas.nacos.NacosConfigurator;
import com.mantas.nacos.NacosProperties;
import com.mantas.tapd.config.NacosTapdxProperties;
import com.mantas.tapd.config.TapdConfigProperties;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * tapd 的项目(project)相关
 */
@Slf4j
public class ProjectService {

    private NacosProperties nacosTapdxConf;

    private NacosConfigurationParser<TapdConfigProperties> configParser = new NacosConfigurationJsonParser(TapdConfigProperties.class);

    public ProjectService(NacosTapdxProperties nacosTapdxConf) {
        this.nacosTapdxConf = nacosTapdxConf;
    }

    public List<Project> getProjects() {
        List<Project> projects = null;
        try {
            TapdConfigProperties config = NacosConfigurator.getConfig(nacosTapdxConf, configParser);
            projects = config.getProjects();
        } catch (Exception e) {
            log.warn("get projects from nacos error ", e);
        }
        return projects;
    }
}

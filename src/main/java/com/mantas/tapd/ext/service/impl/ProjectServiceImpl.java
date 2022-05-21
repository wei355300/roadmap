package com.mantas.tapd.ext.service.impl;

import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mantas.nacos.NacosConf;
import com.mantas.nacos.NacosConfigurator;
import com.mantas.tapd.ext.config.NacosTapdxConf;
import com.mantas.tapd.ext.config.TapdConfigProperties;
import com.mantas.tapd.ext.dto.Project;
import com.mantas.tapd.ext.dto.mapper.ProjectConvert;
import com.mantas.tapd.ext.dto.tapd.TapdProject;
import com.mantas.tapd.ext.service.ProjectService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private NacosConf nacosTapdxConf;

    public ProjectServiceImpl(NacosTapdxConf nacosTapdxConf) {
        this.nacosTapdxConf = nacosTapdxConf;
    }

//    @Override
//    public boolean addProject(Project project) {
//        boolean ret = true;
//        List<Project> projects = getProjects();
//        projects.add(project);
//        try {
//            NacosConfigurator.publishConfig(nacosTapdxConf, projects);
//        } catch (NacosException e) {
//            log.warn("update tapd projects configuration error", e);
//            ret = false;
//        }
//        return ret;
//    }
//
//    @Override
//    public boolean delProject(Project project) {
//        boolean ret = true;
//        List<Project> projects = getProjects();
//        projects.removeIf(p -> p.getId().equals(project.getId()));
//        try {
//            NacosConfigurator.publishConfig(nacosTapdxConf, projects);
//        } catch (NacosException e) {
//            log.warn("update tapd projects configuration error", e);
//            ret = false;
//        }
//        return ret;
//    }

    @Override
    public List<Project> getProjects() {
        List<TapdProject> projects = null;
        try {
            TapdConfigProperties config = NacosConfigurator.getConfig(nacosTapdxConf, TapdConfigProperties.class);
            projects = config.getProjects();
        } catch (NacosException e) {
            e.printStackTrace();
            log.warn("get projects from nacos error ", e);
        } catch (JsonProcessingException e) {
            log.warn("parse data error ", e);
        }
        return ProjectConvert.INSTANCE.toProjects(projects);
    }
}

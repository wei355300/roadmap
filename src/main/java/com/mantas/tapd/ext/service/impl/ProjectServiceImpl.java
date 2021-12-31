package com.mantas.tapd.ext.service.impl;

import com.alibaba.nacos.api.exception.NacosException;
import com.google.common.reflect.TypeToken;
import com.mantas.nacos.NacosConf;
import com.mantas.nacos.NacosConfigurator;
import com.mantas.nacos.NacosTapdxConf;
import com.mantas.tapd.ext.conf.TapdProject;
import com.mantas.tapd.ext.dto.Project;
import com.mantas.tapd.ext.dto.mapper.ProjectConvert;
import com.mantas.tapd.ext.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProjectServiceImpl implements ProjectService {

    private NacosConf nacosTapdxConf;
    private NacosConfigurator nacosConfigurator;

    public ProjectServiceImpl(@Autowired NacosTapdxConf nacosTapdxConf, @Autowired NacosConfigurator nacosConfigurator) {
        this.nacosConfigurator = nacosConfigurator;
        this.nacosTapdxConf = nacosTapdxConf;
    }

    @Override
    public boolean addProject(Project project) {
        boolean ret = true;
        List<Project> projects = getProjects();
        projects.add(project);
        try {
            nacosConfigurator.publishConfig(nacosTapdxConf, projects);
        } catch (NacosException e) {
            log.warn("update tapd projects configuration error", e);
            ret = false;
        }
        return ret;
    }

    @Override
    public boolean delProject(Project project) {
        boolean ret = true;
        List<Project> projects = getProjects();
        projects.removeIf(p -> p.getId().equals(project.getId()));
        try {
            nacosConfigurator.publishConfig(nacosTapdxConf, projects);
        } catch (NacosException e) {
            log.warn("update tapd projects configuration error", e);
            ret = false;
        }
        return ret;
    }

    @Override
    public List<Project> getProjects() {
        List<TapdProject> projects = null;
        try {
            projects = nacosConfigurator.getConfig(nacosTapdxConf, new TypeToken<List<TapdProject>>() {
            }.getType());
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return ProjectConvert.INSTANCE.toProjects(projects);
    }
}

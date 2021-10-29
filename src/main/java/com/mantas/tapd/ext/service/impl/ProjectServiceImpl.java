package com.mantas.tapd.ext.service.impl;

import com.mantas.tapd.ext.conf.TapdConf;
import com.mantas.tapd.ext.conf.TapdProject;
import com.mantas.tapd.ext.dto.Project;
import com.mantas.tapd.ext.dto.mapper.ProjectConvert;
import com.mantas.tapd.ext.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private TapdConf tapdConf;

    @Autowired
    public ProjectServiceImpl(TapdConf tapdConf) {
        this.tapdConf = tapdConf;
    }

    @Override
    public List<Project> getProjects() {
        List<TapdProject> projects = tapdConf.getProjects();
        return ProjectConvert.INSTANCE.toProjects(projects);
    }
}

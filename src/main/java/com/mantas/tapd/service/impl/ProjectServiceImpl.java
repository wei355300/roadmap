package com.mantas.tapd.service.impl;

import com.mantas.tapd.conf.TapdConf;
import com.mantas.tapd.conf.TapdProject;
import com.mantas.tapd.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private TapdConf tapdConf;

    @Override
    public List<TapdProject> listProjects() {
        return tapdConf.getProjects();
    }
}

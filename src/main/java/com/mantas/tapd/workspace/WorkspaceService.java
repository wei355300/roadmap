package com.mantas.tapd.workspace;

import com.mantas.tapd.config.TapdConfigProperties;
import com.mantas.tapd.dto.TapdAuth;
import com.mantas.tapd.project.Project;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class WorkspaceService {

    private TapdConfigProperties properties;

    public WorkspaceService(TapdConfigProperties properties) {
        this.properties = properties;
    }

    public List<Project> getProjects() {
        return properties.getProjects();
    }

    public TapdAuth getAuth() {
        return properties.getAuth();
    }
}

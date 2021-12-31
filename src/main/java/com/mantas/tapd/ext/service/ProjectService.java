package com.mantas.tapd.ext.service;

import com.mantas.tapd.ext.dto.Project;

import java.util.List;

/**
 * tapd 的项目(project)相关
 */
public interface ProjectService {

    boolean addProject(Project project);

    boolean delProject(Project project);

    List<Project> getProjects();
}

package com.mantas.tapd.service;


import com.mantas.tapd.conf.TapdProject;

import java.util.List;

/**
 * tapd 的项目(project)相关
 */
public interface ProjectService {

    List<TapdProject> listProjects();
}

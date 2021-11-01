package com.mantas.tapd.ext.service;

import com.mantas.tapd.ext.dto.ProjectComp;
import com.mantas.tapd.ext.dto.Trace;

import java.util.List;

public interface WorkerBoardService {

    List<ProjectComp> getProjects();

    /**
     * 获取指定项目里的迭代的所有的人员的所有任务
     * 包括: story, bug, task
     */
//    List<Trace> getTraces(Integer projectId, List<String> iterationIds);
//
//    /**
//     * 获取指定项目里的迭代的人员的所有任务
//     * 包括: story, bug, task
//     */
//    List<Trace> getTraces(Integer projectId, List<String> iterationIds, List<String> roleIds);
//
//    /**
//     * {@link #getTraces(Integer, List, List)}
//     */
//    List<Trace> getTraces(ProjectComp project);

    /**
     * 获取指定项目里的迭代的所有的人员的所有任务
     * 包括: story, bug, task
     */
    List<Trace> getTraces(List<ProjectComp> project);
}

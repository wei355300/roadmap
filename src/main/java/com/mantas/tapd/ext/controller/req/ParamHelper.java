package com.mantas.tapd.ext.controller.req;

import com.mantas.tapd.ext.dto.ProjectComp;
import com.mantas.tapd.ext.dto.mapper.ProjectConvert;

import java.util.List;

public class ParamHelper {

    public static ProjectComp toProject(GetTraceProjectParam param) {
        return ProjectConvert.INSTANCE.toProjectComp(param);
    }

    public static List<ProjectComp> toProjects(List<GetTraceProjectParam> params) {
        return ProjectConvert.INSTANCE.toProjectComps(params);
    }
}

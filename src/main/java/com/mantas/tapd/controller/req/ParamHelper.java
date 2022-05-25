package com.mantas.tapd.controller.req;

import com.mantas.tapd.dto.ProjectComp;
import com.mantas.tapd.dto.mapper.ProjectConvert;

import java.util.List;

public class ParamHelper {

    public static ProjectComp toProject(GetTraceProjectParam param) {
        return ProjectConvert.INSTANCE.toProjectComp(param);
    }

    public static List<ProjectComp> toProjects(List<GetTraceProjectParam> params) {
        return ProjectConvert.INSTANCE.toProjectComps(params);
    }
}

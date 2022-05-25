package com.mantas.tapd.dto.mapper;

import com.mantas.tapd.controller.req.GetTraceProjectParam;
import com.mantas.tapd.dto.Project;
import com.mantas.tapd.dto.ProjectComp;
import com.mantas.tapd.dto.TapdProject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProjectConvert {

    ProjectConvert INSTANCE = Mappers.getMapper(ProjectConvert.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name")
    })
    Project toProject(TapdProject project);

    List<Project> toProjects(List<TapdProject> projects);

    ProjectComp toProjectComp(GetTraceProjectParam project);

    List<ProjectComp> toProjectComps(List<GetTraceProjectParam> projects);
}

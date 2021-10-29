package com.mantas.tapd.ext.dto.mapper;

import com.mantas.tapd.ext.conf.TapdProject;
import com.mantas.tapd.ext.dto.Project;
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
}

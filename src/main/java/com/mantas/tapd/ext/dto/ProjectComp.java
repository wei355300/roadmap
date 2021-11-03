package com.mantas.tapd.ext.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

@Data
@ToString
@NoArgsConstructor
public class ProjectComp extends Project{

    private Collection<Iteration> iterations;
    private Collection<Role> roles;

    public ProjectComp(Project project) {
        setId(project.getId());
        setName(project.getName());
    }
}

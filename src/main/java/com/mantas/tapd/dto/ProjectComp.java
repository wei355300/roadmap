package com.mantas.tapd.dto;

import com.mantas.tapd.iteration.Iteration;
import com.mantas.tapd.project.Project;
import com.mantas.tapd.user.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

@Data
@ToString
@NoArgsConstructor
public class ProjectComp extends Project {

    private Collection<Iteration> iterations;
    private Collection<Role> roles;
    private String startDate;
    private String endDate;
    private String[] status;

    public ProjectComp(Project project) {
        setId(project.getId());
        setName(project.getName());
    }
}

package com.mantas.tapd.service.test;

import com.mantas.tapd.dto.Role;
import com.mantas.tapd.dto.Worker;
import com.mantas.tapd.service.ProjectService;
import com.mantas.tapd.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ProjectService projectService;

    @Test
    public void testGetStories() throws IOException {

        List<Role> roles = roleService.getRolesByProject(projectService.getProjects().get(0).getId());

        assertThat(roles).isNotNull();

        roles.forEach(r -> System.out.println(r.toString()));
    }

    @Test
    public void testGetUsers() throws IOException {

        Integer projectId = projectService.getProjects().get(0).getId();
        List<Worker> workers = roleService.getUsersByProject(projectId);

        assertThat(workers).isNotNull();

        workers.forEach(r -> System.out.println(r.toString()));
    }
}

package com.mantas.tapd.ext.service.test;

import com.mantas.tapd.ext.conf.TapdConf;
import com.mantas.tapd.ext.dto.Project;
import com.mantas.tapd.ext.dto.Role;
import com.mantas.tapd.ext.dto.Worker;
import com.mantas.tapd.ext.service.ProjectService;
import com.mantas.tapd.ext.service.RoleService;
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
    private TapdConf tapdConf;

    @Autowired
    private RoleService roleService;

    @Test
    public void testGetStories() throws IOException {
        System.out.println(tapdConf.toString());

        List<Role> roles = roleService.getRolesByProject(tapdConf.getProjects().get(0).getId());

        assertThat(roles).isNotNull();

        roles.forEach(r -> System.out.println(r.toString()));
    }

    @Test
    public void testGetUsers() throws IOException {
        System.out.println(tapdConf.toString());

        Integer projectId = tapdConf.getProjects().get(0).getId();
        List<Worker> workers = roleService.getWorkersByRole(projectId);

        assertThat(workers).isNotNull();

        workers.forEach(r -> System.out.println(r.toString()));
    }
}

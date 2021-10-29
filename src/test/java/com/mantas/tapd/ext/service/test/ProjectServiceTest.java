package com.mantas.tapd.ext.service.test;

import com.mantas.tapd.ext.conf.TapdConf;
import com.mantas.tapd.ext.dto.Project;
import com.mantas.tapd.ext.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class ProjectServiceTest {
    @Autowired
    private TapdConf tapdConf;

    @Autowired
    private ProjectService projectService;

    @Test
    public void testGetStories() throws IOException {
        System.out.println(tapdConf.toString());

        List<Project> projects = projectService.getProjects();

        assertThat(projects).isNotNull();

        projects.forEach(p -> System.out.println(p.toString()));
    }
}

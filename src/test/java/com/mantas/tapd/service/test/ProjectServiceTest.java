package com.mantas.tapd.service.test;

import com.mantas.tapd.conf.TapdConf;
import com.mantas.tapd.conf.TapdProject;
import com.mantas.tapd.service.ProjectService;
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

        List<TapdProject> projects = projectService.listProjects();

        assertThat(projects).isNotNull();

        projects.forEach(p -> System.out.println(p.toString()));
    }
}

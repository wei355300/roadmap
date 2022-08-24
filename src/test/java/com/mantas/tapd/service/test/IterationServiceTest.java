package com.mantas.tapd.service.test;

import com.mantas.tapd.iteration.Iteration;
import com.mantas.tapd.iteration.IterationService;
import com.mantas.tapd.project.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class IterationServiceTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private IterationService iterationService;

    @Test
    public void testGetStories() throws IOException {

        List<Iteration> iterations = iterationService.getIterationsByProject(projectService.getProjects().get(0).getId());

        assertThat(iterations).isNotNull();

        iterations.forEach(i -> System.out.println(i.toString()));
    }
}

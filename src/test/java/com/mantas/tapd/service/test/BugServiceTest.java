package com.mantas.tapd.service.test;

import com.mantas.tapd.bug.Bug;
import com.mantas.tapd.exception.TapdException;
import com.mantas.tapd.iteration.Iteration;
import com.mantas.tapd.project.Project;
import com.mantas.tapd.bug.BugService;
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
public class BugServiceTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private IterationService iterationService;

    @Autowired
    private BugService bugService;

    @Test
    public void testGetBugs() throws IOException, TapdException {

        List<Project> projects = projectService.getProjects();

        Integer projectId = projects.get(0).getId();

        List<Iteration> iterations = iterationService.getIterations(projectId);

        Iteration iteration = iterations.get(0);

        Iteration iter = new Iteration();
        iter.setId("1164372788001001247");
        List<Bug> bugs = bugService.getByIteration(64372788, iter.getId());

        assertThat(bugs).isNotNull();

        bugs.forEach(i -> System.out.println(i.toString()));
    }
}

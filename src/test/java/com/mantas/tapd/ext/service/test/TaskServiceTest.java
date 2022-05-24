package com.mantas.tapd.ext.service.test;

import com.mantas.tapd.ext.dto.Iteration;
import com.mantas.tapd.ext.dto.Project;
import com.mantas.tapd.ext.dto.Task;
import com.mantas.tapd.ext.service.IterationService;
import com.mantas.tapd.ext.service.ProjectService;
import com.mantas.tapd.ext.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class TaskServiceTest {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private IterationService iterationService;

    @Autowired
    private TaskService taskService;

    @Test
    public void testGetTasks() throws IOException {

        List<Project> projects = projectService.getProjects();

        Integer projectId = projects.get(0).getId();

        List<Iteration> iterations = iterationService.getIterationsByProject(projectId);

        Iteration iteration = iterations.get(0);

        Iteration iter = new Iteration();
        iter.setId("1148309202001001230");
        List<Task> tasks = taskService.getByIterations(48309202, iter);

        assertThat(tasks).isNotNull();

        tasks.forEach(i -> System.out.println(i.toString()));
    }
}

package com.mantas.tapd.ext.service.test;

import com.mantas.tapd.ext.dto.ProjectComp;
import com.mantas.tapd.ext.dto.WorkerTrace;
import com.mantas.tapd.ext.service.WorkerBoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class WorkerBoardServiceTester {

    @Autowired
    private WorkerBoardService workerBoardService;

    @Test
    public void testGetProjects() throws IOException {

        List<ProjectComp> projects = workerBoardService.getProjects();
//        System.out.println(tapdConf.toString());
//
//        List<Project> projects = projectService.getProjects();

        assertThat(projects).isNotNull();

        projects.forEach(p -> System.out.println(p.toString()));
    }

    @Test
    public void testGetTraces() throws IOException {
        List<ProjectComp> projects = workerBoardService.getProjects();
        Collection<WorkerTrace> traces = workerBoardService.getTraces(projects.get(0));

        assertThat(traces).isNotNull();

        traces.forEach(t -> System.out.println(t.toString()));
    }
}

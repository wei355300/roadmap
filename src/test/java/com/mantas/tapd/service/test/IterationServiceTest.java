package com.mantas.tapd.service.test;

import com.mantas.tapd.exception.TapdException;
import com.mantas.tapd.iteration.Iteration;
import com.mantas.tapd.iteration.IterationService;
import com.mantas.tapd.project.Project;
import com.mantas.tapd.project.ProjectService;
import com.mantas.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public void testGetIterations() throws IOException, TapdException {

        List<Iteration> iterations = iterationService.getIterations(projectService.getProjects().get(0).getId());

        assertThat(iterations).isNotNull();

        System.out.println(JsonUtils.toPrettyJson(iterations));
    }

    @Test
    public void testClose() throws TapdException {
        Integer projectId = getProject().getId();
        String iterationId = getUnCloseIteration(projectId).getId();
        String optUser = "韦峰";
        projectId = null;
        Boolean ret = iterationService.close(projectId, iterationId, optUser);

        assertThat(ret).isTrue();
    }

    @Test
    public void testCloseMore() throws TapdException {
        Integer projectId = 22259671;
        Integer daysBefore = 7;
        String optUser = "韦峰";
        iterationService.close(projectId, daysBefore, optUser);
    }

    private Project getProject() {
        return projectService.getProjects().get(0);
    }

    private Iteration getUnCloseIteration(Integer projectId) throws TapdException {
        List<Iteration> list = iterationService.getIterations(projectId);
        Iteration iteration = null;
        LocalDate now = LocalDate.now();

        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Iteration i : list) {
            LocalDate endDate = LocalDate.parse(i.getEndDate(), dtFormatter);
            if(endDate.plusDays(14).isBefore(now)) {
                return i;
            }
        }
        if (iteration == null) {
            throw new NullPointerException();
        }
        return iteration;
    }
}

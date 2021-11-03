package com.mantas.tapd.ext.service.test;

import com.mantas.tapd.ext.conf.TapdConf;
import com.mantas.tapd.ext.dto.Iteration;
import com.mantas.tapd.ext.service.IterationService;
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
    private TapdConf tapdConf;

    @Autowired
    private IterationService iterationService;

    @Test
    public void testGetStories() throws IOException {
        System.out.println(tapdConf.toString());

        List<Iteration> iterations = iterationService.getIterationsByProject(tapdConf.getProjects().get(0).getId());

        assertThat(iterations).isNotNull();

        iterations.forEach(i -> System.out.println(i.toString()));
    }
}

package com.mantas.tapd.controller.test;

import com.mantas.tapd.iteration.Iteration;
import com.mantas.tapd.iteration.IterationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class IterationControllerTester {

    String iterationId = "1122259671001000629";

    @MockBean
    private IterationService iterationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCloseIteration() throws Exception {

        ResultActions resultActions = mockMvc.perform(
                put("/api/tapd/iteration/close")
                        .param("projectId","")
                        .param("iterationId", "")
                        .param("optUser", "")
        );
        resultActions.andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testListStoriesByIterationId() throws Exception {

        when(iterationService.list(null)).thenReturn(buildIterationList());

        //ok test
        ResultActions resultActions = mockMvc.perform(get("/api/tapd/iteration/list"));
        resultActions.andExpect(status().isOk()).andDo(print());
    }

    private List<Iteration> buildIterationList() {
        List<Iteration> iterations = new ArrayList<>();
        Iteration iteration = new Iteration();
//        iteration
        iterations.add(iteration);
        return iterations;
    }
}
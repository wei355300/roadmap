package com.mantas.tapd.controller.mock.service;

import com.mantas.tapd.controller.IterationController;
import com.mantas.tapd.dto.Iteration;
import com.mantas.tapd.service.IterationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest(IterationController.class)
public class IterationControllerTester {

    String iterationId = "1122259671001000629";

    @MockBean
    private IterationService iterationService;

    @Autowired
    private MockMvc mockMvc;

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
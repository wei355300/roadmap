package com.mantas.tapd.controller.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class IterationControllerTester {

    String iterationId = "1122259671001000629";

//    @MockBean
//    private IterationService iterationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCloseIteration() throws Exception {

        ResultActions resultActions = mockMvc.perform(
                put("/api/tapd/iteration/close")
                        .param("projectId","41045808")
                        .param("iterationId", "1141045808001001339")
                        .param("optUser", "韦峰")
                        .header("Token", "ecd96370-d3d8-4b8e-9fc8-085348da1c8b")
        );
        resultActions.andExpect(status().isOk()).andDo(print());
    }

//    @Test
//    public void testListStoriesByIterationId() throws Exception {
//
//        when(iterationService.list(null)).thenReturn(buildIterationList());
//
//        //ok test
//        ResultActions resultActions = mockMvc.perform(get("/api/tapd/iteration/list"));
//        resultActions.andExpect(status().isOk()).andDo(print());
//    }
//
//    private List<Iteration> buildIterationList() {
//        List<Iteration> iterations = new ArrayList<>();
//        Iteration iteration = new Iteration();
////        iteration
//        iterations.add(iteration);
//        return iterations;
//    }
}
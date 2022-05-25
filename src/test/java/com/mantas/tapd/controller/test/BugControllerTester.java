package com.mantas.tapd.controller.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class BugControllerTester {

    String projectId = "22259671";
    String iterationId = "1122259671001001233";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListStoriesByIterationId() throws Exception {
        //ok test
        ResultActions resultActions = mockMvc.perform(get("/api/tapd/bug/list").param("projectId", projectId).param("iterationId", iterationId));
        resultActions.andExpect(status().isOk()).andDo(print());
    }
}
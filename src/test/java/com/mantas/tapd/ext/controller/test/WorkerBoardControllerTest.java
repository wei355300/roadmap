package com.mantas.tapd.ext.controller.test;

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
public class WorkerBoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetWorkerBoardProjects() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/api/tapd/worker/board/projects"));
        resultActions.andExpect(status().isOk()).andDo(print());
    }
}

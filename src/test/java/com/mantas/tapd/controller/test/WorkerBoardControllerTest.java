package com.mantas.tapd.controller.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    public void testGetTraces() throws Exception {
        String token = "8f4c4a72-abc7-402a-b964-25343588d7e9";
        String body = "[{\"id\":68292382,\"name\":\"研发基地\",\"startDate\":\"2023-04-17\",\"endDate\":\"2023-04-22\"}]";

        ResultActions resultActions = mockMvc.perform(post("/api/tapd/worker/board/traces")
                                                              .content(body)
                                                              .header("Token", token)
                                                              .contentType(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk()).andDo(print());
    }
}

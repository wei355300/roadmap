package com.mantas.tapd.ext.controller.road;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StoryControllerFollowTester {

    String iterationId = "1122259671001000629";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListStoriesByIterationId() throws Exception {
        //ok test
        ResultActions resultActions = mockMvc.perform(get("/api/tapd/story/list").param("iterationId", iterationId));
        resultActions.andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testListStoriesMissIterationId() throws Exception {

        ResultActions resultActionsBad = mockMvc.perform(get("/api/tapd/story/list"));
        resultActionsBad.andExpect(status().isBadRequest()).andDo(print());
    }
}
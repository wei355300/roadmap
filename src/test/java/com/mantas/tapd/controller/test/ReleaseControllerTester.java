package com.mantas.tapd.controller.test;

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
public class ReleaseControllerTester {

    String day = "2020-09-07";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListReleaseByStartDate() throws Exception {
        //ok test
        ResultActions resultActions = mockMvc.perform(get("/api/tapd/release/list").param("day", day));
        resultActions.andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testListReleaseMiss() throws Exception {

        ResultActions resultActionsBad = mockMvc.perform(get("/api/tapd/release/list"));
        resultActionsBad.andExpect(status().isBadRequest()).andDo(print());
    }
}
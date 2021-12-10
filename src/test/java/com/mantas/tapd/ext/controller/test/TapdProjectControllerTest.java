package com.mantas.tapd.ext.controller.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
public class TapdProjectControllerTest {

//    @Autowired
//    private MockMvc mockMvc;

    @Test
    public void testTapdProjectList() {

//        ResultActions resultActions = mockMvc.perform(get("/api/tapd/story/list").param("iterationId", iterationId));
//        resultActions.andExpect(status().isOk()).andDo(print());
    }
}

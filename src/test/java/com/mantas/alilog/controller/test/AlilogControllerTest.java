package com.mantas.alilog.controller.test;

import com.mantas.utils.JsonUtils;
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
public class AlilogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetEntities() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/api/alilog/entities"));
        resultActions.andExpect(status().isOk()).andDo(print());
        String body = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(body);
    }
}

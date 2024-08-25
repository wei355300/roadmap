package com.mantas.zentao.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ZentaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetToken() throws Exception {
        //ok test
        ResultActions resultActions = mockMvc.perform(get("/api/zentao/token").header("token", "1fd6ab1c4-9e0b-938726eaf50e"));
        resultActions.andExpect(status().isOk()).andDo(print());
    }
}
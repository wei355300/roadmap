package com.mantas.tapd.ext.controller.mock;

import com.mantas.account.controller.AccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest(AccountController.class)
public class AccountControllerTester {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogin() throws Exception {
        //ok test
        ResultActions resultActions = mockMvc.perform(post("/api/account/login"));
        resultActions.andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testGetCurrentUser() throws Exception {
        //ok test
        ResultActions resultActions = mockMvc.perform(get("/api/account/current"));
        resultActions.andExpect(status().isOk()).andDo(print());
    }
}
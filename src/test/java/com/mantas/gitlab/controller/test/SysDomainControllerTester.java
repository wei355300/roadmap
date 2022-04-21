package com.mantas.gitlab.controller.test;

import com.mantas.MantasXApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ContextConfiguration(classes = {Configurations.class})
//@WebMvcTest(SysDomainController.class)

/**
 * @AutoConfigureMockMvc 与 @SpringBootTest 结合, 类似于启动了整个应用, 模拟web环境测试
 * @WebMvcTest 与 @ContextConfiguration 及 @MockBean 配合, 可以仅模拟测试controll层,
 * 而service层通过@MockBean模拟
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = MantasXApplication.class)
public class SysDomainControllerTester {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetDomain() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/git/file/domains.json"));
        resultActions.andExpect(status().isOk());

        System.out.println("result:");
        System.out.println(resultActions.andReturn().getResponse().getContentAsString());

    }
}

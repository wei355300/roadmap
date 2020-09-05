package com.mantas.tapd.controller.mock.service;

import com.mantas.tapd.controller.DeveloperController;
import com.mantas.tapd.dto.Role;
import com.mantas.tapd.service.DeveloperService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest(DeveloperController.class)
public class DeveloperControllerTester {

    @MockBean
    private DeveloperService developerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListRoles() throws Exception {

        //ok test
        when(developerService.listRoles()).thenReturn(buildRoles());


        ResultActions resultActions = mockMvc.perform(get("/api/tapd/developer/roles"));
        resultActions.andExpect(status().isOk()).andDo(print());

    }

    private List<Role> buildRoles() {
        List<Role> roles = new ArrayList<>();

        Role r1 = new Role();
        r1.setId("1");
        r1.setName("1-name");

        roles.add(r1);

        Role r2 = new Role();
        r2.setId("2");
        r2.setName("2-name");

        roles.add(r2);

        return roles;
    }
}
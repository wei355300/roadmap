package com.mantas.alilog.controller.test;

import com.mantas.alilog.controller.req.QueryParams;
import com.mantas.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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

    @Test
    public void testQuery() throws Exception {
        String entity = "B2B订货平台";
        String logStore = "小程序";
        String query = "登录小程序用户数";
        int fromTime = (int) (System.currentTimeMillis()/1000 - 3600);
        int toTime = fromTime + 3600;

        QueryParams params = new QueryParams();
        params.setEntity(entity);
        params.setStore(logStore);
        params.setQuery(query);
        params.setFromTime(fromTime);
        params.setToTime(toTime);

        String paramsBody = JsonUtils.toJson(params);

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance().path("/api/alilog/query");

        URI path = builder.encode().build().toUri();

//        ResultActions resultActions = mockMvc.perform(get(path).content(paramsBody).contentType(MediaType.APPLICATION_JSON));
        ResultActions resultActions = mockMvc.perform(get(path).param("params", paramsBody));
        resultActions.andExpect(status().isOk()).andDo(print());
        String body = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(body);
    }
}

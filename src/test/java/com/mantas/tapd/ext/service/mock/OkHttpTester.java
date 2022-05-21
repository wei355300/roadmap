package com.mantas.tapd.ext.service.mock;

import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.ext.config.TapdConfigProperties;
import com.mantas.tapd.origin.TapdURL;
import com.mantas.tapd.ext.dto.tapd.TapdStoryData;
import com.mantas.okhttp.OkHttp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class OkHttpTester {

    @Autowired
    private TapdConfigProperties tapdConf;

    @Autowired
    private OkHttp okHttp;

    @Test
    public void testGetStories() throws IOException {

//        when(tapdConf.getDefaultWorkspaceId()).thenReturn("22259671");
//        when(tapdConf.getBasicAuthId()).thenReturn("btn3ZoxJ");
//        when(tapdConf.getBasicAuthPwd()).thenReturn("8639CB5F-E5ED-0E2A-57FB-53505E79B782");

        System.out.println(tapdConf.toString());

        List<ParamPair> params = new ArrayList<>();
        params.add(new ParamPair(TapdURL.PARAM.ITERATION_ID, "1122259671001000682"));
        TapdStoryData ret = okHttp.get(TapdURL.URL.STORIES, params, TapdStoryData.class);

        assertThat(ret).isNotNull();

        assertThat(Objects.isNull(ret.getData())).isFalse();

        ret.getData().stream().forEach( t -> {
            System.out.println(t.getStory().getName());
        });
    }
}

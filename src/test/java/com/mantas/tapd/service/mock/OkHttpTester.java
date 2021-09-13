package com.mantas.tapd.service.mock;

import com.mantas.connector.ParamPair;
import com.mantas.tapd.conf.TapdConf;
import com.mantas.tapd.conf.TapdURL;
import com.mantas.tapd.dto.tapd.TapdStoryData;
import com.mantas.connector.OkHttp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class OkHttpTester {

    @Mock
    private TapdConf tapdConf;

    @Autowired
    private OkHttp okHttp;

    @Test
    public void testGetStories() throws IOException {

        when(tapdConf.getDefaultWorkspaceId()).thenReturn("22259671");
        when(tapdConf.getBasicAuthId()).thenReturn("btn3ZoxJ");
        when(tapdConf.getBasicAuthPwd()).thenReturn("8639CB5F-E5ED-0E2A-57FB-53505E79B782");

        List<ParamPair> params = new ArrayList<>();
        params.add(new ParamPair(TapdURL.TAPD_PARAM_ITERATION_ID, "1122259671001000682"));
        TapdStoryData ret = okHttp.get(TapdURL.TAPD_URL_STORIES, params, TapdStoryData.class);

        assertThat(ret).isNotNull();

        assertThat(Objects.isNull(ret.getData())).isFalse();

        ret.getData().stream().forEach( t -> {
            System.out.println(t.getStory().getName());
        });
    }
}

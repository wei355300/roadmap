package com.mantas.tapd.service.mock;

import com.mantas.tapd.conf.TapdConf;
import com.mantas.tapd.dto.tapd.TapdStoryData;
import com.mantas.tapd.connector.OkHttp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OkHttpTester {

    @Mock
    private TapdConf tapdConf;

    @InjectMocks
    private OkHttp okHttp;

    @Test
    public void testGetStories() throws IOException {

        when(tapdConf.getDefaultWorkspaceId()).thenReturn("22259671");
        when(tapdConf.getBasicAuthId()).thenReturn("btn3ZoxJ");
        when(tapdConf.getBasicAuthPwd()).thenReturn("8639CB5F-E5ED-0E2A-57FB-53505E79B782");

        TapdStoryData ret = okHttp.get(TapdConf.TAPD.TAPD_URL_STORIES, setParam(TapdConf.TAPD.TAPD_PARAM_ITERATION_ID, "1122259671001000682"), TapdStoryData.class);

        assertThat(ret).isNotNull();

        assertThat(Objects.isNull(ret.getData())).isFalse();

        ret.getData().stream().forEach( t -> {
            System.out.println(t.getStory().getName());
        });
    }

    private Map<String, String> setParam(String key, String value) {
        Map params = new HashMap();
        params.put(key, value);
        return params;
    }
}

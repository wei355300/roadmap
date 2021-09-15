package com.mantas.tapd;

import com.mantas.connector.OkHttp;
import com.mantas.tapd.conf.TapdConf;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class ConfigurationTest {

    @Autowired
    private TapdConf tapdConf;

    @Autowired
    private OkHttp okHttp;

    @Test
    public void testConfigurationTapdInit() {
        System.out.println(tapdConf);
        System.out.println(okHttp);
    }
}

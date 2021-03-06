package com.mantas.tapd;

import com.mantas.okhttp.OkHttp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class ConfigurationTest {

    @Autowired
    private OkHttp okHttp;

    @Test
    public void testConfigurationTapdInit() {
        System.out.println(okHttp);
    }
}

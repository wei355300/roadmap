package com.mantas.tapd.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @ConditionalOnProperty(name = "tapd", matchIfMissing = true)
    @Bean
    public TapdConf tapdConf() throws Exception {
        return new TapdConf();
    }
}

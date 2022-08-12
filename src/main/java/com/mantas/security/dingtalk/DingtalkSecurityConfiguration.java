package com.mantas.security.dingtalk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mantas.security.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "auth.dingtalk", name = "client-id", havingValue = "")
public class DingtalkSecurityConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "auth.dingtalk")
    public DingtalkProperties dingtalkProperties() {
        return new DingtalkProperties();
    }

    @Bean
    public DingtalkDetailServices dingtalkDetailServices(DingtalkProperties dingtalkProperties,
                                                         AccountService accountService) throws Exception {
        return new DingtalkDetailServices(dingtalkProperties, accountService);
    }

    @Bean
    public DingtalkAuthenticationCallbackFilter dingtalkAuthenticationCallbackFilter(HttpSecurity http,
                                                                             DingtalkProperties dingtalkProperties,
                                                                             ObjectMapper objectMapper,
                                                                             DingtalkDetailServices dingtalkDetailServices) throws Exception {
        DingtalkAuthenticationProvider dingtalkAuthenticationProvider = new DingtalkAuthenticationProvider(dingtalkDetailServices);
        DingtalkAuthenticationManager dingtalkAuthenticationManager = new DingtalkAuthenticationManager(dingtalkAuthenticationProvider);
        DingtalkAuthenticationCallbackFilter dingtalkAuthenticationCallbackFilter = new DingtalkAuthenticationCallbackFilter(dingtalkProperties.getAuthenticationCallbackUrl(), dingtalkAuthenticationManager);
        dingtalkAuthenticationCallbackFilter.setAuthenticationSuccessHandler(new DingtalkAuthenticationSuccessHandler(objectMapper));

        return dingtalkAuthenticationCallbackFilter;
    }
}

package com.mantas.security.dingtalk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mantas.security.account.service.AccountService;
import com.mantas.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "auth.dingtalk", name = "callback-uri", havingValue = "")
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
    public DingtalkAuthenticationFilter dingtalkAuthenticationFilter(HttpSecurity http,
                                                                     ObjectMapper objectMapper,
                                                                     DingtalkDetailServices dingtalkDetailServices) throws Exception {
        log.debug("build dingtalkAuthenticationFilter");
        DingtalkAuthenticationProvider dingtalkAuthenticationProvider = new DingtalkAuthenticationProvider(dingtalkDetailServices);
        DingtalkAuthenticationManager dingtalkAuthenticationManager = new DingtalkAuthenticationManager(dingtalkAuthenticationProvider);
        DingtalkAuthenticationFilter dingtalkAuthenticationFilter = new DingtalkAuthenticationFilter(DingtalkAuthenticationFilter.DINGTALK_AUTHENTICATION_CALLBACK_URI, dingtalkAuthenticationManager);
        dingtalkAuthenticationFilter.setAuthenticationSuccessHandler(new DingtalkAuthenticationSuccessHandler(objectMapper));

        return dingtalkAuthenticationFilter;
    }
}

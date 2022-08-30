package com.mantas.security.dingtalk;

import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mantas.nacos.NacosConfigurator;
import com.mantas.security.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Slf4j
@Configuration
public class DingtalkSecurityConfiguration {

    @Bean
    public DingtalkProperties dingtalkProperties(@Autowired NacosDingtalkProperties dingtalkConf) throws NacosException, JsonProcessingException {

        return NacosConfigurator.getConfig(dingtalkConf, DingtalkProperties.class);
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

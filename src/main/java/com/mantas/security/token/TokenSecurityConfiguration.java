package com.mantas.security.token;

import com.mantas.security.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Slf4j
@Configuration
public class TokenSecurityConfiguration {

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter(HttpSecurity http,
                                                               AccountService accountService) throws Exception {
        log.debug("addTokenAuthenticationFilter into SecurityFilterChain");
        TokenDetailServices tokenDetailServices = new TokenDetailServices(accountService);
        TokenAuthenticationProvider tokenAuthenticationProvider = new TokenAuthenticationProvider(tokenDetailServices);
        TokenAuthenticationManager tokenAuthenticationManager = new TokenAuthenticationManager(tokenAuthenticationProvider);
        TokenAuthenticationFilter tokenAuthenticationFilter = new TokenAuthenticationFilter("/api/**", tokenAuthenticationManager);
        return tokenAuthenticationFilter;
    }
}

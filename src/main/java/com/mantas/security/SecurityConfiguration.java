package com.mantas.security;

import com.mantas.security.account.mapper.AccountMapper;
import com.mantas.security.account.service.AccountService;
import com.mantas.security.authority.AuthorityUrlCheckerAuthorizationManager;
import com.mantas.security.dingtalk.DingtalkAuthenticationCallbackFilter;
import com.mantas.security.token.TokenAuthenticationFilter;
import com.mantas.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
public class SecurityConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AuthorityUriMatcher resourceAuthorizedMatcher() {
        return new AuthorityUriMatcher();
    }

    @Bean
    @ConditionalOnMissingBean
    public AccountService accountService(AccountMapper accountMapper, UserService userService) {
        return new AccountService(userService, accountMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthorityUrlCheckerAuthorizationManager authorityUriPermissionCheckerAuthorizationManager() {
        return new AuthorityUrlCheckerAuthorizationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           AuthorityUriMatcher authorityUriMatcher,
                                           DingtalkAuthenticationCallbackFilter dingtalkAuthenticationCallbackFilter,
                                           TokenAuthenticationFilter tokenAuthenticationFilter,
                                           AuthorityUrlCheckerAuthorizationManager authorityUriPermissionCheckerAuthorizationManage) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .cors().disable()
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .anonymous().disable()
                .rememberMe().disable();
        http.addFilterBefore(dingtalkAuthenticationCallbackFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(tokenAuthenticationFilter, DingtalkAuthenticationCallbackFilter.class);
        http.authorizeHttpRequests().mvcMatchers("/base/auth/dingtalk/metainfo").permitAll();
        http.authorizeHttpRequests(authz -> {
            authz.mvcMatchers(authorityUriMatcher.getPermitUris()).permitAll()
                    .mvcMatchers(authorityUriMatcher.getAuthenticatedUris()).access(authorityUriPermissionCheckerAuthorizationManage)
                    .mvcMatchers(authorityUriMatcher.getDenyUris()).denyAll()
                    .anyRequest().authenticated();
        });

        return http.build();
    }
}

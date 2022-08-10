package com.mantas.security;

import com.mantas.security.account.mapper.AccountMapper;
import com.mantas.security.account.service.AccountService;
import com.mantas.security.authority.AuthorityUrlCheckerAuthorizationManager;
import com.mantas.security.token.TokenAuthenticationFilter;
import com.mantas.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@MapperScan(basePackages = "com.mantas.security.account.mapper")
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
                                           TokenAuthenticationFilter tokenAuthenticationFilter,
                                           AuthorityUrlCheckerAuthorizationManager authorityUriPermissionCheckerAuthorizationManage
    ) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .cors().and()
                .csrf().and()
                .httpBasic().and()
                .formLogin().and()
                .anonymous().and()
                .rememberMe()
                .disable();
//        http
//                .authorizeRequests()
//                .antMatchers("/api/admin/**").hasAnyRole("ADMIN")
//                .antMatchers("/api/wechat/**").hasAnyRole("WECHAT")
//                .antMatchers("/api/store/**").hasAnyRole("STORE")
//                .anyRequest().authenticated()
//                .antMatchers("/**").permitAll();

        log.debug("add DingtalkAuthenticationFilter into  SecurityFilterChain");
//        http.addFilterBefore(dingtalkAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//        http.authenticationManager(authenticationManager);

        // 除了 登录接口, 其它接口都需要进行权限授权
//        http
//                .authorizeHttpRequests(authz ->
//                                authz.mvcMatchers("/api/**").access(new AuthorityUriPermissionCheckerAuthorizationManager<>())
//                                        .anyRequest().authenticated()
//                                        .mvcMatchers("/api/login").permitAll()
//                                        .mvcMatchers("/api/**")
//                                        .access(new AuthorityUriPermissionCheckerAuthorizationManager())
//                                        .mvcMatchers("/**").denyAll()
//                );
        http.authorizeHttpRequests()
                .mvcMatchers(authorityUriMatcher.getPermitUris()).permitAll()
                .mvcMatchers(authorityUriMatcher.getAuthenticatedUris()).access(authorityUriPermissionCheckerAuthorizationManage)
                .mvcMatchers(authorityUriMatcher.getDenyUris()).denyAll()
                .anyRequest().authenticated()
        ;

        return http.build();
    }
}

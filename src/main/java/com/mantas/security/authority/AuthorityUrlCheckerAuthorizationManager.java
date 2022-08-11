package com.mantas.security.authority;

import com.mantas.security.account.dto.Account;
import com.mantas.security.authority.dto.Authority;
import com.mantas.security.token.TokenAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.List;
import java.util.function.Supplier;

/**
 *
 * 判断账号是否具备request的url对应的权限.
 *
 * 提取request的url, 与账号的权限集合进行比较,
 * 如果权限集合包含url(及对应的method), 表明具备权限, 授权通过
 *
 * 注:
 * 账号是否具备权限 是 通过 http请求的url+method 结合, 与账号的权限集合进行比较,
 * Spring Security 的 GrantedAuthority 不符合需求,
 * 所以不使用 Authentication 实例中的 GrantedAuthority 中的数据.
 *
 * 查看 {@link com.mantas.security.token.TokenAuthenticationProvider}
 */
@Slf4j
public class AuthorityUrlCheckerAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    public AuthorityUrlCheckerAuthorizationManager() {
    }

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        AuthorizationManager.super.verify(authentication, requestAuthorizationContext);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        return hasPermission(authentication, requestAuthorizationContext);
    }

    private AuthorizationDecision hasPermission(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        // authentication 为 TokenAuthenticationToken 实例
        // TokenAuthenticationToken 中存放着账号的所有权限
        TokenAuthenticationToken tokenAuthenticationToken = (TokenAuthenticationToken) authentication.get();
        Account account = tokenAuthenticationToken.getAccount();
        Authority authority = new Authority(requestAuthorizationContext.getRequest().getRequestURI(), requestAuthorizationContext.getRequest().getMethod());

        return new AuthorizationDecision(AuthorityGrantor.hasAuthority(account, authority));
    }
}

package com.mantas.security.token;

import com.mantas.security.account.dto.Account;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class TokenAuthenticationToken extends AbstractAuthenticationToken {

    private String token;
    private Account account;

    public TokenAuthenticationToken(String token, Account account) {
        super(null);
        this.token = token;
        this.account = account;
        setAuthenticated(true);
    }

    public TokenAuthenticationToken(String token) {
        super(null);
        this.token = token;
        setAuthenticated(false);
    }

    public static TokenAuthenticationToken unauthenticated(String token) {
        return new TokenAuthenticationToken(token);
    }

    public static TokenAuthenticationToken authenticated(String token, Account account) {
        return new TokenAuthenticationToken(token, account);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    public String getToken() {
        return token;
    }

    public Account getAccount() {
        return account;
    }
}

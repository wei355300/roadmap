package com.mantas.security;

import com.mantas.security.account.dto.Account;
import com.mantas.security.token.TokenAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticatedHolder {

    public static Account getAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (! (authentication instanceof TokenAuthenticationToken)) {
            throw new BadCredentialsException("账号异常");
        }
        TokenAuthenticationToken tokenAuthenticationToken = (TokenAuthenticationToken) authentication;
        return tokenAuthenticationToken.getAccount();
    }
}

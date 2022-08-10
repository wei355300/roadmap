package com.mantas.security.dingtalk;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class DingtalkAuthenticationToken extends AbstractAuthenticationToken {

    private String authCode;
//    private Account account;

    public DingtalkAuthenticationToken(String authCode) {
        super(null);
        this.authCode = authCode;
        setAuthenticated(false);
    }

//    public DingtalkAuthenticationToken(String authCode, Account account) {
//        super(null);
//        this.authCode = authCode;
//        this.account = account;
//        setAuthenticated(true);
//    }

    public static DingtalkAuthenticationToken unauthenticated(String authCode) {
        return new DingtalkAuthenticationToken(authCode);
    }

//    public static DingtalkAuthenticationToken authenticated(String authCode, Account account) {
//        return new DingtalkAuthenticationToken(authCode, account);
//    }

    public String getAuthCode() {
        return authCode;
    }

    @Override
    public String getCredentials() {
        return authCode;
    }

    @Override
    public String getPrincipal() {
        return authCode;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.authCode = null;
    }
}

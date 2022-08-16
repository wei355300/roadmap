package com.mantas.security;

import lombok.Getter;

@Getter
public class AuthorityUriMatcher {

    private String[] permitUris;


    private String[] denyUris;

    private String[] authenticatedUris;

    public AuthorityUriMatcher() {
        init();
    }

    /**
     * 从配置中获取请求url的限制规则
     */
    private void init() {
        permitUris = new String[]{"/base/**"};

        denyUris = new String[] {"/**"};

        authenticatedUris = new String[] {"/api/**"};
    }
}

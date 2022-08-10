package com.mantas.security;

import com.mantas.security.dingtalk.DingtalkAuthenticationFilter;
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
        permitUris = new String[]{"/api/login",
                DingtalkAuthenticationFilter.DINGTALK_AUTHENTICATION_CALLBACK_URI};

        denyUris = new String[] {"/**"};

        authenticatedUris = new String[] {"/api/**"};
    }
}

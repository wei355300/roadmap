package com.mantas.security.dingtalk;

import lombok.Data;

@Data
public class DingtalkProperties {

    private String clientId;
    private String clientSecret;
    private String grantType;
    private String corpId;
    private String authenticationCallbackUrl;
}

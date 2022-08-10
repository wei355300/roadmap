package com.mantas.security.dingtalk;

import lombok.Data;

@Data
public class DingtalkProperties {

    private String callbackUri;
    private String clientId;
    private String clientSecret;
    private String grantType;
}

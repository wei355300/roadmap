package com.mantas.security.dingtalk;

import lombok.Data;

/**
 * 用于构造钉钉(扫码)登录的基础信息,
 *
 * 参数描述参考: https://open.dingtalk.com/document/isvapp-server/obtain-identity-credentials
 */
@Data
public class DingtalkAuthMetaInfo {

    private String redirectUri;
    private String responseType = "code";
    private String clientId;
    private String scope = "openid";
    private String state;
    private String prompt = "consent";
    private String corpId;
}

package com.mantas.security.dingtalk;

import com.aliyun.dingtalkcontact_1_0.models.GetUserHeaders;
import com.aliyun.dingtalkcontact_1_0.models.GetUserResponse;
import com.aliyun.dingtalkcontact_1_0.models.GetUserResponseBody;
import com.aliyun.dingtalkoauth2_1_0.Client;
import com.aliyun.dingtalkoauth2_1_0.models.GetUserTokenRequest;
import com.aliyun.dingtalkoauth2_1_0.models.GetUserTokenResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.mantas.user.dto.UserInfo;
import com.mantas.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DingtalkClient {
    private DingtalkProperties dingtalkProperties;

    private Client client;

    public DingtalkClient(DingtalkProperties dingtalkProperties) throws DingtalkException {
        this.dingtalkProperties = dingtalkProperties;
        client = authClient();
    }

    private Client authClient() throws DingtalkException {
        try {
            Config config = new Config();
            config.protocol = "https";
            config.regionId = "central";
            return new Client(config);
        } catch (Exception e) {
            throw new DingtalkException("init ding talk client error ", e);
        }

    }

    /**
     * 获取钉钉登录(扫码登录)所需的原始信息
     * 用于构造出发到钉钉服务器的请求参数
     */
    public DingtalkAuthMetaInfo getAuthMetaInfo() {
        DingtalkAuthMetaInfo dingtalkAuthMetaInfo = new DingtalkAuthMetaInfo();
        dingtalkAuthMetaInfo.setRedirectUri(dingtalkProperties.getAuthenticationCallbackUrl());
        dingtalkAuthMetaInfo.setClientId(dingtalkProperties.getClientId());
        dingtalkAuthMetaInfo.setCorpId(dingtalkProperties.getCorpId());
        return dingtalkAuthMetaInfo;
    }


    public UserInfo getDingtalkUserinfo(String authCode) throws DingtalkException {
        try {
            GetUserTokenRequest getUserTokenRequest = new GetUserTokenRequest()
                    //应用基础信息-应用信息的AppKey,请务必替换为开发的应用AppKey
                    .setClientId(dingtalkProperties.getClientId())
                    //应用基础信息-应用信息的AppSecret，,请务必替换为开发的应用AppSecret
                    .setClientSecret(dingtalkProperties.getClientSecret())
                    .setCode(authCode)
                    .setGrantType(dingtalkProperties.getGrantType());
            GetUserTokenResponse getUserTokenResponse = client.getUserToken(getUserTokenRequest);
            //获取用户个人token
            String accessToken = getUserTokenResponse.getBody().getAccessToken();

            com.aliyun.dingtalkcontact_1_0.Client client = contactClient();
            GetUserHeaders getUserHeaders = new GetUserHeaders();
            getUserHeaders.xAcsDingtalkAccessToken = accessToken;
            //获取用户个人信息，如需获取当前授权人的信息，unionId参数必须传me
            GetUserResponse getUserResponse = client.getUserWithOptions("me", getUserHeaders, new RuntimeOptions());
            GetUserResponseBody getUserResponseBody = getUserResponse.getBody();
            UserInfo user = DingtalkUserInfoMapStruct.ins.toUser(getUserResponseBody);
            log.info("Dingtalk userInfo : {}", JsonUtils.toJson(getUserResponseBody));
            return user;
        } catch (Exception e) {
            throw new DingtalkException("get ding talk user failed", e);
        }
    }

    /**
     *
     * 参考:
     * 获取用户通讯录个人信息
     * https://open.dingtalk.com/document/isvapp-server/dingtalk-retrieve-user-information
     *
     * @return
     * @throws Exception
     */
    private static com.aliyun.dingtalkcontact_1_0.Client contactClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkcontact_1_0.Client(config);
    }
}

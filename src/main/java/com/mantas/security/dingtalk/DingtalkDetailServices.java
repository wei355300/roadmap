package com.mantas.security.dingtalk;

import com.aliyun.dingtalkcontact_1_0.models.GetUserHeaders;
import com.aliyun.dingtalkcontact_1_0.models.GetUserResponse;
import com.aliyun.dingtalkcontact_1_0.models.GetUserResponseBody;
import com.aliyun.dingtalkoauth2_1_0.Client;
import com.aliyun.dingtalkoauth2_1_0.models.GetUserTokenRequest;
import com.aliyun.dingtalkoauth2_1_0.models.GetUserTokenResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.mantas.security.account.dto.Account;
import com.mantas.security.account.service.AccountService;
import com.mantas.user.dto.UserInfo;
import com.mantas.user.service.UserService;
import com.mantas.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class DingtalkDetailServices {

    private DingtalkProperties dingtalkProperties;
    private AccountService accountService;

    private Client client;

    public DingtalkDetailServices(DingtalkProperties dingtalkProperties,
                                  AccountService accountService) throws Exception {
        this.dingtalkProperties = dingtalkProperties;
        this.accountService = accountService;
        client = authClient();
    }

    public Account getAccountByAuthCode(String authCode) throws Exception {

        String dingtalkAccessToken = getDingtalkAccessToken(authCode);
        UserInfo user =  getDingtalkUserinfo(dingtalkAccessToken);

        Account account = accountService.getAccountByMobile(user.getMobile());
        if (Objects.isNull(account)) {
            account = accountService.createAccount(user);
        }
        return account;
    }

    private Client authClient() throws Exception {
        Config config = new Config();
        config.protocol = "https";
        config.regionId = "central";
        return new Client(config);
    }

    private String getDingtalkAccessToken(String authCode) throws Exception {
        GetUserTokenRequest getUserTokenRequest = new GetUserTokenRequest()
                //应用基础信息-应用信息的AppKey,请务必替换为开发的应用AppKey
                .setClientId(dingtalkProperties.getClientId())
                //应用基础信息-应用信息的AppSecret，,请务必替换为开发的应用AppSecret
                .setClientSecret(dingtalkProperties.getClientSecret())
                .setCode(authCode)
                .setGrantType(dingtalkProperties.getGrantType());
        GetUserTokenResponse getUserTokenResponse = client.getUserToken(getUserTokenRequest);
        log.info("get Dingtalk User Token: {}", getUserTokenResponse);
        //获取用户个人token
        String accessToken = getUserTokenResponse.getBody().getAccessToken();
        return accessToken;
    }

    private UserInfo getDingtalkUserinfo(String accessToken) throws Exception {
        com.aliyun.dingtalkcontact_1_0.Client client = contactClient();
        GetUserHeaders getUserHeaders = new GetUserHeaders();
        getUserHeaders.xAcsDingtalkAccessToken = accessToken;
        //获取用户个人信息，如需获取当前授权人的信息，unionId参数必须传me
        GetUserResponse getUserResponse = client.getUserWithOptions("me", getUserHeaders, new RuntimeOptions());
        GetUserResponseBody getUserResponseBody = getUserResponse.getBody();
        UserInfo user = DingtalkUserInfoMapStruct.ins.toUser(getUserResponseBody);
        log.info("Dingtalk userInfo : {}", JsonUtils.toJson(getUserResponseBody));
        return user;
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

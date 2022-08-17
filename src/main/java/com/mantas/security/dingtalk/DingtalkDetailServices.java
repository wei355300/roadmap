package com.mantas.security.dingtalk;

import com.mantas.security.account.dto.Account;
import com.mantas.security.account.exception.AccountNotExistException;
import com.mantas.security.account.exception.AccountUserNotExistException;
import com.mantas.security.account.service.AccountService;
import com.mantas.user.dto.UserInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class DingtalkDetailServices {

    private AccountService accountService;
    private DingtalkClient dingtalkClient;

    public DingtalkDetailServices(DingtalkProperties dingtalkProperties,
                                  AccountService accountService) throws Exception {
        this.dingtalkClient = new DingtalkClient(dingtalkProperties);
        this.accountService = accountService;
    }

    public Account getAccountByAuthCode(String authCode) throws DingtalkException {
        UserInfo dingtalkUserinfo =  dingtalkClient.getDingtalkUserinfo(authCode);
        if(Objects.isNull(dingtalkUserinfo.getMobile())) {
            //未授权手机号, 无法建立账号
            throw new DingtalkException("未授权手机号");
        }

        // 如果用户不存在, 创建账号
        // 用户存在, 账号不存在, 补充账号
        //
        Account account;
        try {
            account = accountService.getAccountWithAuthoritiesByMobile(dingtalkUserinfo.getMobile());
        } catch (AccountUserNotExistException | AccountNotExistException aune) {
            account = accountService.createAccount(dingtalkUserinfo);
        }
        return account;
    }

    /**
     * 获取钉钉登录(扫码登录)所需的原始信息
     * 用于构造出发到钉钉服务器的请求参数
     */
    public DingtalkAuthMetaInfo getAuthMetaInfo() {
        return dingtalkClient.getAuthMetaInfo();
    }
}

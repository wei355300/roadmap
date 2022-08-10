package com.mantas.security.token;

import com.mantas.security.account.dto.Account;
import com.mantas.security.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenDetailServices {

    private AccountService accountService;

    public TokenDetailServices(AccountService accountService) {
        this.accountService = accountService;
    }

    public Account getAccountByToken(String token) {
        log.debug("getUserByToken: {}", token);
        return accountService.getAccountByToken(token);
    }
}

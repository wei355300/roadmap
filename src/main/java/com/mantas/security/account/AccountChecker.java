package com.mantas.security.account;

import com.mantas.security.account.dto.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
public class AccountChecker {

    public void check(Account account) {
        if (!account.getNonLocked()) {
            log.debug("user account {} is locked", account.getToken());
            throw new LockedException("User account is locked");
        }
        if (!account.getStatus()) {
            log.debug("user account {} is disabled", account.getToken());
            throw new DisabledException("User is disabled");
        }
        if (Objects.isNull(account.getExpiration()) || account.getExpiration().isBefore(LocalDateTime.now())) {
            log.debug("user account {} is disabled", account.getToken());
            throw new AccountExpiredException("User is expired");
        }
    }
}

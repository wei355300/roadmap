package com.mantas.security.account.controller;

import com.mantas.controller.R;
import com.mantas.security.AuthenticatedHolder;
import com.mantas.security.account.dto.Account;
import com.mantas.security.account.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 更新登录账号的Token效期, 并重置Token
     * @return 返回账号信息
     */
    @PostMapping("/token")
    public R<Account> updateTokenExpiration() {
        Account account = AuthenticatedHolder.getAccount();
        account = accountService.updateToken(account);
        return R.success(account);
    }

    /**
     * 获取登录账号信息
     * @return
     */
    @GetMapping("/info")
    public R<Account> getAccountInfo() {
        Account account = AuthenticatedHolder.getAccount();
        return R.success(account);
    }

    /**
     * 获取账号列表(分页)
     * @return
     */
    @GetMapping("/list")
    public R<Account> listAccounts() {
        return null;
    }

    /**
     * 更新指定账号的的Token效期
     * @return
     */
    @GetMapping("/{accountId}/token")
    public R updateTokenExpirationById() {
        return null;//todo
    }

    /**
     * 更新指定账号的信息, 并返回更新后的账号信息
     * @return account
     */
    @PatchMapping("/{accountId}/info")
    public R<Account> updateAccount() {
        return null;
    }

    /**
     * 锁定指定的账号
     * @return
     */
    @PatchMapping("/{accountId}/locked")
    public R disable() {
        return R.success();
    }

    /**
     * 解锁指定的账号
     * @return
     */
    @PatchMapping("/{accountId}/unlock")
    public R enable() {
        return R.success();
    }
}

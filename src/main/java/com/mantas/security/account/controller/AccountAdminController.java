package com.mantas.security.account.controller;

import com.mantas.controller.R;
import com.mantas.security.account.dto.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/account")
public class AccountAdminController {

    @GetMapping("/list")
    public R<Account> listAccount() {
        return null; //todo
    }

    /**
     * 更新账号信息, 并返回更新后的账号信息
     * @return account
     */
    @PatchMapping("/info")
    public R<Account> updateAccount() {
        return null;
    }

    @PatchMapping("/disable")
    public R disable() {
        return R.success();
    }

    @PatchMapping("/enable")
    public R enable() {
        return R.success();
    }
}

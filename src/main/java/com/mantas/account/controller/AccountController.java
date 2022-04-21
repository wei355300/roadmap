package com.mantas.account.controller;

import com.mantas.account.mock.CurrentAuthority;
import com.mantas.account.mock.LoginAccount;
import com.mantas.controller.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @GetMapping("/current")
    public R getCurrentUser() {
        return R.success(new LoginAccount());
    }

    @PostMapping("/login")
    public R login() {
        return R.success(new CurrentAuthority());
    }
}

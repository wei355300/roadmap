package com.mantas.security.account.controller;

import com.mantas.controller.R;
import com.mantas.security.account.dto.Account;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
public class AccountController {

    @GetMapping("/info")
    public R<Account> getAccount() {
        return null;//todo
    }
}

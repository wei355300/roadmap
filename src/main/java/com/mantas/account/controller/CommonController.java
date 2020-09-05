package com.mantas.account.controller;

import com.mantas.base.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comm")
public class CommonController {

    @GetMapping("/notices")
    public R getNotices() {
        return R.result(0, "");
    }
}

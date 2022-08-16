package com.mantas.security.dingtalk;

import com.mantas.controller.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/base/auth/dingtalk")
public class DingtalkController {

    private DingtalkDetailServices dingtalkDetailServices;

    public DingtalkController(DingtalkDetailServices dingtalkDetailServices) {
        this.dingtalkDetailServices = dingtalkDetailServices;
    }

    @GetMapping("/metainfo")
    public R getAuthMetaInfo() {
        DingtalkAuthMetaInfo metaInfo = dingtalkDetailServices.getAuthMetaInfo();
        return R.success(metaInfo);
    }
}

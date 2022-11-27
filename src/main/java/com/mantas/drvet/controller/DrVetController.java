package com.mantas.drvet.controller;

import com.mantas.controller.R;
import com.mantas.drvet.mapper.DrvetMobileMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/api/drvet")
public class DrVetController {

    private DrvetMobileMapper mapper;

    public DrVetController(DrvetMobileMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping("match/mobile")
    public R matchMobile(@RequestParam("mobile") String mobile) {
        int count = mapper.countByMobile(mobile);
        if (count > 0) {
            return R.success();
        }
        return R.result(-1, "");
    }
}

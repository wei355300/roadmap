package com.mantas.drvet.controller;

import com.mantas.controller.R;
import com.mantas.drvet.mapper.DrvetMobileMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/base/api/drvet")
public class DrVetController {

    private DrvetMobileMapper mapper;

    public DrVetController(DrvetMobileMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping("/match/mobile")
    public R matchMobile(@RequestParam("mobile") String mobile) {
        int count = mapper.countByMobile(mobile);
        if (count > 0) {
            return R.success();
        }
        return R.result(-1, "");
    }

    @PostMapping("/add/mobile")
    public R addMobile(@RequestParam("mobile") String mobile) {
        String m = mapper.getMobile(mobile);
        if (Objects.nonNull(m)) {
            return R.result(-1, "手机号已存在");
        }
        mapper.addMobile(mobile);
        return R.success();
    }
}

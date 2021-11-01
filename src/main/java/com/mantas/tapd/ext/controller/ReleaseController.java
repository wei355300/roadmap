package com.mantas.tapd.ext.controller;

import com.mantas.base.R;
import com.mantas.tapd.ext.dto.Release;
import com.mantas.tapd.ext.service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("/api/tapd/release")
@Deprecated
public class ReleaseController {

    @Autowired
    private ReleaseService releaseService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public R<List<Release>> getReleases(@RequestParam("day") @NotNull(message = "参数[时间]不能为空") String day) {
        return R.success(releaseService.list(day));
    }
}

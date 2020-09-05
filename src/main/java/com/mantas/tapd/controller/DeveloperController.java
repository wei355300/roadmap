package com.mantas.tapd.controller;

import com.mantas.base.R;
import com.mantas.tapd.dto.Role;
import com.mantas.tapd.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/tapd/developer")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    @ResponseBody
    public R<List<Role>> getRoles() {
        return R.success(developerService.listRoles());
    }
}

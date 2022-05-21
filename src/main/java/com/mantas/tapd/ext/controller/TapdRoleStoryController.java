package com.mantas.tapd.ext.controller;

import com.mantas.controller.R;
import com.mantas.tapd.ext.dto.Role;
import com.mantas.tapd.ext.dto.Story;
import com.mantas.tapd.ext.dto.Worker;
import com.mantas.tapd.ext.service.RoleService;
import com.mantas.tapd.ext.service.StoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tapd")
public class TapdRoleStoryController {

    private RoleService roleService;

    @Autowired
    public TapdRoleStoryController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/role/list")
    public R getRoles(@RequestParam("projectId") Integer projectId) {
        List<Role> roles = roleService.getRolesByProject(projectId);
        return R.success(roles);
    }

    @GetMapping("/user/list")
    public R getUsers(@RequestParam("projectId") Integer projectId) {
        List<Worker> roles = roleService.getUsersByProject(projectId);
        return R.success(roles);
    }
}

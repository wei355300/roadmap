package com.mantas.tapd.ext.controller;

import com.mantas.controller.R;
import com.mantas.tapd.ext.dto.Bug;
import com.mantas.tapd.ext.dto.Role;
import com.mantas.tapd.ext.dto.Worker;
import com.mantas.tapd.ext.service.BugService;
import com.mantas.tapd.ext.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tapd/bug")
public class TapdBugController {

    private BugService bugService;

    @Autowired
    public TapdBugController(BugService bugService) {
        this.bugService = bugService;
    }

    @GetMapping("/list")
    public R getBugs(@RequestParam("projectId") Integer projectId, @RequestParam("iterationId") String iterationId) {
        List<Bug> bugs = bugService.getByIteration(projectId, iterationId);
        return R.success(bugs);
    }
}

package com.mantas.tapd.controller;

import com.mantas.controller.R;
import com.mantas.security.AuthenticatedHolder;
import com.mantas.tapd.iteration.IterationService;
import com.mantas.tapd.story.Story;
import com.mantas.tapd.story.StoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/tapd/iteration")
public class TapdIterationController {

    private IterationService iterationService;

    @Autowired
    public TapdIterationController(IterationService iterationService) {
        this.iterationService = iterationService;
    }

    @PutMapping("/close")
    public R closeIteration(@RequestParam("projectId") Integer projectId, @RequestParam("iterationId") String iterationId) {
        String optUser = AuthenticatedHolder.getAccount().getName();
        Boolean isClosed = iterationService.close(projectId, iterationId, optUser);
        return isClosed? R.success() : R.result(-1, "未知原因关闭失败");
    }
}

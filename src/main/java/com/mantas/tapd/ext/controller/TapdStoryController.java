package com.mantas.tapd.ext.controller;

import com.mantas.controller.R;
import com.mantas.tapd.ext.dto.Story;
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
@RequestMapping("/api/tapd/story")
public class TapdStoryController {

    private StoryService storyService;

    @Autowired
    public TapdStoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/list")
    public R getStories(@RequestParam("projectId") Integer projectId, @RequestParam("iterationId") String iterationId) {
        List<Story> stories = storyService.getByIteration(projectId, iterationId);
        return R.success(stories);
    }
}

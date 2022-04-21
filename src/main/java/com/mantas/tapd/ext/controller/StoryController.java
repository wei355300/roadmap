package com.mantas.tapd.ext.controller;

import com.mantas.tapd.ext.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/tapd/story")
@Deprecated
public class StoryController {

    @Autowired
    private StoryService storyService;

//    @RequestMapping(value = "/list/iteration", method = RequestMethod.GET)
//    @ResponseBody
//    public R<List<Story>> getStoriesByIteration(@RequestParam("iterationId") @NotNull(message = "参数[迭代]不能为空") String iterationId) {
//        return R.success(storyService.listByIteration(iterationId));
//    }
//
//    @RequestMapping(value = "/list/release", method = RequestMethod.GET)
//    @ResponseBody
//    public R<List<Story>> getStoriesByRelease(@RequestParam("releaseId") @NotNull(message = "参数[发布]不能为空") String releaseId) {
//        return R.success(storyService.listByRelease(releaseId));
//    }
}

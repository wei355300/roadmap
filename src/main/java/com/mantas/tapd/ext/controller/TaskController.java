package com.mantas.tapd.ext.controller;

import com.mantas.tapd.ext.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/tapd/task")
@Deprecated
public class TaskController {

    @Autowired
    private StoryService storyService;

//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @ResponseBody
//    public R<List<Story>> getTasks() {
//        return R.success(storyService.listByIteration("1122259671001000629"));
//    }
}

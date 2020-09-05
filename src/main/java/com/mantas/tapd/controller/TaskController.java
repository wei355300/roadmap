package com.mantas.tapd.controller;

import com.mantas.base.R;
import com.mantas.tapd.dto.Story;
import com.mantas.tapd.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/tapd/task")
public class TaskController {

    @Autowired
    private StoryService storyService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public R<List<Story>> getTasks() {
        return R.success(storyService.listByIteration("1122259671001000629"));
    }
}

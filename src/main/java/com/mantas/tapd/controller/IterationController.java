package com.mantas.tapd.controller;

import com.mantas.tapd.dto.Iteration;
import com.mantas.tapd.service.IterationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/tapd/iteration")
public class IterationController {

    @Autowired
    private IterationService iterationService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Iteration> getRoles() {
        return iterationService.list(null);
    }
}

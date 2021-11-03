package com.mantas.tapd.ext.controller;

import com.mantas.tapd.ext.dto.Iteration;
import com.mantas.tapd.ext.service.IterationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/tapd/iteration")
@Deprecated
public class IterationController {

    @Autowired
    private IterationService iterationService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Iteration> getRoles() {
        return iterationService.list(null);
    }
}

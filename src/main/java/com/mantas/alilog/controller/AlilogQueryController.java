package com.mantas.alilog.controller;

import com.mantas.alilog.service.AlilogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController("/api/alilog")
public class AlilogQueryController {

    private AlilogService alilogService;

    public AlilogQueryController(@Autowired AlilogService alilogService) {
        this.alilogService = alilogService;
    }

    @GetMapping("/entities")
    public Collection<String> listLogEntities() {
        return alilogService.getLogEntities();
    }

    @GetMapping("/entity/query")
    public String getQueryOfEntity(@RequestParam("path") String path) throws Exception {
        return alilogService.getQueryOfEntity(path);
    }
}

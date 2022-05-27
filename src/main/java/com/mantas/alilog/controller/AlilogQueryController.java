package com.mantas.alilog.controller;

import com.mantas.alilog.controller.req.QueryParams;
import com.mantas.alilog.controller.req.QueryParamsEditor;
import com.mantas.alilog.dto.LogEntity;
import com.mantas.alilog.dto.LogResLine;
import com.mantas.alilog.service.AlilogService;
import com.mantas.controller.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/alilog")
public class AlilogQueryController {

    private AlilogService alilogService;

    public AlilogQueryController(@Autowired AlilogService alilogService) {
        this.alilogService = alilogService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(QueryParams.class, new QueryParamsEditor());
    }

    @GetMapping("/entities")
    public R listLogEntities() {
        Collection<LogEntity> entities = alilogService.getLogEntities();
        return R.success(entities);
    }

    @GetMapping("/query")
    public R getQueryOfEntity(@Valid @RequestParam("params") QueryParams params) throws Exception {
        List<LogResLine> lines = alilogService.query(params.getEntity(), params.getStore(), params.getQuery(), params.getFromTime(), params.getToTime());
        return R.success(lines);
    }
}

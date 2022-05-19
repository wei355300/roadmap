package com.mantas.alilog.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mantas.alilog.controller.req.QueryParams;
import com.mantas.alilog.dto.LogEntity;
import com.mantas.alilog.dto.LogResLine;
import com.mantas.alilog.service.AlilogService;
import com.mantas.controller.R;
import com.mantas.controller.ResponseJsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @JsonView(ResponseJsonView.Public.class)
    @GetMapping("/entities")
    public R listLogEntities() {
        Collection<LogEntity> entities = alilogService.getLogEntities();

//        ObjectMapper objectMapper = new ObjectMapper();
//        String result = null;
//        try {
//            objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
//            result = objectMapper
//                    .writerWithView(ResponseJsonView.Public.class)
//                    .writeValueAsString(entities);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        log.debug("listLogEntities: \n {}", result);

        return R.success(entities);
    }

//    @JsonView(ResponseJsonView.Public.class)
    @GetMapping("/query")
    public R getQueryOfEntity(@Valid @RequestBody QueryParams params) throws Exception {
        List<LogResLine> lines =  alilogService.query(params.getEntity(), params.getStore(), params.getQuery(), params.getFromTime(), params.getToTime());
        return R.success(lines);
    }
}

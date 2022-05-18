package com.mantas.alilog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.mantas.controller.ResponseJsonView;
import lombok.Data;

@Data
public class LogQuery {

    @JsonView(ResponseJsonView.Public.class)
    private String name;
    private String topic;
    @JsonView(ResponseJsonView.Public.class)
    private String filter;
}

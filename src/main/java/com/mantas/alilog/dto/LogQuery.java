package com.mantas.alilog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.mantas.controller.ResponseJsonView;
import lombok.Data;

@JsonView(ResponseJsonView.Public.class)
@Data
public class LogQuery {

    private String name;
    private String topic;
    private String filter;
}

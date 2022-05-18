package com.mantas.alilog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.mantas.controller.ResponseJsonView;
import lombok.Data;

import java.util.List;

@Data
public class LogEntity {

    private String accessId;
    private String accessKey;
    private String host;
    @JsonView(ResponseJsonView.Public.class)
    private String entity;
    @JsonView(ResponseJsonView.Public.class)
    private String desc;
    @JsonView(ResponseJsonView.Public.class)
    private List<LogStore> logStores;
}

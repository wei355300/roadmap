package com.mantas.alilog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.mantas.controller.ResponseJsonView;
import lombok.Data;

import java.util.List;

@Data
public class LogStore {

    @JsonView(ResponseJsonView.Public.class)
    private String name;
    private String project;
    private String logStore;
    @JsonView(ResponseJsonView.Public.class)
    private List<LogQuery> logQueries;
}

package com.mantas.alilog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LogStore {

    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String project;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String logStore;
    private List<LogQuery> logQueries;
}

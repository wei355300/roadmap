package com.mantas.alilog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LogEntity {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String accessId;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String accessKey;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String host;
    private String entity;
    private String desc;
    private List<LogStore> logStores;
}

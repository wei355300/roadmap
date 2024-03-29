package com.mantas.tapd.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@EqualsAndHashCode(of = {"id"})
public class Trace {

    private String id;
    private String name;
    private String type;
    private String link;
    private int weight;
    private Boolean done;
    private Integer projectId;
    private String iterationId;
    private String start;
    private String end;
}

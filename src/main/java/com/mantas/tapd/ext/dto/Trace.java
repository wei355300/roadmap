package com.mantas.tapd.ext.dto;

import lombok.Data;

@Data
public class Trace {

    private String id;
    private String name;
    private String type;
    private String link;
    private int weight;
    private boolean done;

    private Integer projectId;
    private String iterationId;
}

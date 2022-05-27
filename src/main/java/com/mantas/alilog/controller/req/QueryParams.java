package com.mantas.alilog.controller.req;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class QueryParams {

    @NotNull
    private String entity;
    @NotNull
    private String store;
    @NotNull
    private String query;
    @Min(1)
    private int fromTime;
    @Min(1)
    private int toTime;
}

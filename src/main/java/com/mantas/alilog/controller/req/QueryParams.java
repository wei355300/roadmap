package com.mantas.alilog.controller.req;

import com.fasterxml.jackson.annotation.JsonView;
import com.mantas.controller.ResponseJsonView;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class QueryParams {

    @JsonView(ResponseJsonView.Public.class)
    @NotNull
    private String entity;
    @JsonView(ResponseJsonView.Public.class)
    @NotNull
    private String store;
    @JsonView(ResponseJsonView.Public.class)
    @NotNull
    private String query;
    @JsonView(ResponseJsonView.Public.class)
    @Min(1)
    private int fromTime;
    @JsonView(ResponseJsonView.Public.class)
    @Min(1)
    private int toTime;
}

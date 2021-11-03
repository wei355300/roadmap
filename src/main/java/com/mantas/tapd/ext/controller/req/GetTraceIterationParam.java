package com.mantas.tapd.ext.controller.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetTraceIterationParam {
    @NotNull
    private String id;
    private String name;
}
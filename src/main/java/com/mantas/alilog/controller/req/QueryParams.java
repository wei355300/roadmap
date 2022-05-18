package com.mantas.alilog.controller.req;

import lombok.Data;

@Data
public class QueryParams {

    private String entity;
    private String store;
    private String query;
    private int fromTime;
    private int toTime;
}

package com.mantas.alilog.dto;

import lombok.Data;

@Data
public class LogQuery {

    private String name;
    private String topic;
    private String filter;
}

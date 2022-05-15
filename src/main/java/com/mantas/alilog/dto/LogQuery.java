package com.mantas.alilog.dto;

import lombok.Data;

@Data
public class LogQuery {

    private String projectName;
    private String logstoreName;
    private String topic;
    private int fromTime;
    private int toTIme;
    private String filter;
}

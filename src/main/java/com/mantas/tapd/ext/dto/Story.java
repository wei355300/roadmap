package com.mantas.tapd.ext.dto;

import lombok.Data;

@Data
public class Story {

    private String id;
    private String[] owner;
    private String name;
    private String description;
    private String status;
    private String[] developer;
    private String priority;
    private String begin;
    private String due;
    private int businessValue;
    private int projectId;
    private String iterationId;
    private String releaseId;
}

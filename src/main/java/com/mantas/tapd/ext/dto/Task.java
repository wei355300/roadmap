package com.mantas.tapd.ext.dto;

import lombok.Data;

@Data
public class Task {

    private String id;
    private String[] owner;
    private String name;
    private String description;
    private String status;
    private String priority;
    private String begin;
    private String due;
    private int projectId;
    private String iterationId;
}

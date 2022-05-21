package com.mantas.tapd.origin.dto;

import lombok.Data;

@Data
public class TapdTaskItem {
    private String id;
    private String name;
    private String description;
    private String workspace_id;
    private String creator;
    private String owner;
    private String status;
    private String developer;
    private String priority;
    private String begin;
    private String due;
    private String business_value;
    private String iteration_id;
    private String release_id;
}
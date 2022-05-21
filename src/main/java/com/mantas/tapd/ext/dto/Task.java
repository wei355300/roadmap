package com.mantas.tapd.ext.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.List;

@Data
public class Task {

    private String id;
    @JsonDeserialize(converter = TapdOwnerConverterOfJsonDeSerialization.class)
    private List<String> owner;
    private String name;
    private String description;
    private String status;
    private String priority;
    private String begin;
    private String due;
    private int projectId;
    private String iterationId;
}

package com.mantas.tapd.story;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mantas.tapd.dto.TapdOwnerConverterOfJsonDeSerialization;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Story {

    private String id;
    @JsonDeserialize(converter = TapdOwnerConverterOfJsonDeSerialization.class)
    private List<String> owner;
    private String name;
    private String description;
    private String status;
    @JsonDeserialize(converter = TapdOwnerConverterOfJsonDeSerialization.class)
    private List<String> developer;
    private String priority;
    private String begin;
    private String due;
    private int businessValue;
    private int workspaceId;
    private String iterationId;
    private String releaseId;
}


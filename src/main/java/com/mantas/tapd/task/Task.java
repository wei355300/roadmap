package com.mantas.tapd.task;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mantas.tapd.dto.TapdOwnerConverterOfJsonDeSerialization;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
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
    @JsonAlias("story_id")
    private String storyId;
    @JsonAlias("iteration_id")
    private String iterationId;
}

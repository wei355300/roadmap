package com.mantas.tapd.ext.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Bug {

    private String id;
    private String title;
    @JsonDeserialize(converter = TapdOwnerConverterOfJsonDeSerialization.class)
    private List<String> developer;
    @JsonDeserialize(converter = TapdOwnerConverterOfJsonDeSerialization.class)
    private List<String> tester;
    private String description;
    private String priority; //优先级
    private String reporter; //创建人
    private String severity; //严重程度
    private String status;
    private int projectId;
    private String iterationId;
}

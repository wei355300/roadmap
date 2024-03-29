package com.mantas.tapd.bug;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mantas.tapd.dto.TapdOwnerConverterOfJsonDeSerialization;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Bug {

    private String id;
    private String title;
    @JsonDeserialize(converter = TapdOwnerConverterOfJsonDeSerialization.class)
    @JsonAlias("de")
    private List<String> developer;
    @JsonDeserialize(converter = TapdOwnerConverterOfJsonDeSerialization.class)
    @JsonAlias("te")
    private List<String> tester;
    private String description;
    private String priority; //优先级
    private String reporter; //创建人
    private String severity; //严重程度
    private String status;
    private String begin;
    private String due;
    private int projectId;
    @JsonAlias("iteration_id")
    private String iterationId;
}

package com.mantas.tapd.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Iteration {

    private String id;
    private String name;
    private String status;
    @JsonAlias("startdate")
    private String startDate;
    @JsonAlias("enddate")
    private String endDate;
}

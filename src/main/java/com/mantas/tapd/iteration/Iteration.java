package com.mantas.tapd.iteration;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Iteration implements Serializable {

    private String id;
    private String name;
    private String status;
    @JsonAlias("startdate")
    private String startDate;
    @JsonAlias("enddate")
    private String endDate;
}

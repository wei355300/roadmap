package com.mantas.tapd.release;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Release {

    private String id;
    private String name;
    private String workspaceId;
    private String description;
    private String startDate;
    private String endDate;
    private String status;
}

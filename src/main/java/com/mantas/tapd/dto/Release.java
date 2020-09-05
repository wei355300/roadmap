package com.mantas.tapd.dto;

import lombok.Data;

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

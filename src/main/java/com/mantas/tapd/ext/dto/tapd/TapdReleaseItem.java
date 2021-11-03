package com.mantas.tapd.ext.dto.tapd;

import lombok.Data;

@Data
public class TapdReleaseItem {

    private String id;
    private String workspace_id;
    private String name;
    private String description;
    private String startdate;
    private String enddate;
    private String creator;
    private String created;
    private String modified;
    private String status;
}

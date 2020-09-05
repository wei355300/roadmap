package com.mantas.tapd.dto.tapd;

import lombok.Data;

@Data
public class TapdStoryItem {
    private String id;
    private String name;
    private String description;
    private String workspace_id;
    private String creator;
    private String owner;
    private String status;
    private String developer;
    private String priority;
    private String iteration_id;
    private String release_id;
}
package com.mantas.tapd.dto;

import com.mantas.tapd.connector.UrlBuiler;
import lombok.Data;

@Data
public class Story {

    private String id;
    private String owner;
    private String name;
    private String description;
    private String workspaceId;
    private String creator;
    private String status;
    private String developer;
    private String priority;
    private String iterationId;

    public String getUrl() {
        return UrlBuiler.buildViewStoryUrl(id);
    }
}

package com.mantas.tapd.ext.dto;

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
        //fixme
        return "";//UrlBuilder.buildViewStoryUrl(id);
    }
}

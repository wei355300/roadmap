package com.mantas.gitlab.config;

import lombok.Data;

@Data
public class GitlabConfigProperties {

    private String projectId;
    private String projectName;
    private String url;
    private String accessToken;
}

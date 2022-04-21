package com.mantas.gitlab.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "gitlab")
public class GitlabConfigProperties {

    private String projectId;
    private String projectName;
    private String url;
    private String accessToken;
}

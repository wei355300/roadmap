package com.mantas.tapd.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
@ConfigurationProperties(prefix = "tapd", ignoreUnknownFields = true)
public class TapdConf {

    private String defaultWorkspaceId;
    private String basicAuthId;
    private String basicAuthPwd;
    private List<TapdWorkspace> workspaces;
}

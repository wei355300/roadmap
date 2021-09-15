package com.mantas.tapd.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "tapd", ignoreUnknownFields = true)
public class TapdConf {

    private String defaultWorkspaceId;
    private String basicAuthId;
    private String basicAuthPwd;
    private List<TapdProject> projects;
}

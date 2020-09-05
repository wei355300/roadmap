package com.mantas.tapd.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "tapd", ignoreUnknownFields = true)
public class TapdConf {

    public interface TAPD {

        String TAPD_URL_STORIES = "https://api.tapd.cn/stories";
        String TAPD_URL_ROLE = "https://api.tapd.cn/roles";
        String TAPD_URL_RELEASE = "https://api.tapd.cn/releases";

        String TAPD_PARAM_WORKSPACE_ID = "workspace_id";
        String TAPD_PARAM_ITERATION_ID = "iteration_id";
        String TAPD_PARAM_RELEASE_ID = "release_id";

        String TAPD_PARAM_START_DATE = "startdate";
        String TAPD_PARAM_END_DATE = "enddate";


    }

    private String defaultWorkspaceId;
    private String basicAuthId;
    private String basicAuthPwd;
}

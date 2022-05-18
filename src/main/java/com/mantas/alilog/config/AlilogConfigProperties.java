package com.mantas.alilog.config;

import com.mantas.gitlab.config.GitlabConfigProperties;
import lombok.Data;

import java.util.List;

@Data
public class AlilogConfigProperties {

    @Data
    public static class AlilogItemConfigProperties {

        private String entity;
        private String desc;
        private String accessId;
        private String accessKey;
        private String host;
        private AlilogItemGitConfigProperties git;
    }

    @Data
    public static class AlilogItemGitConfigProperties extends GitlabConfigProperties {
        private String file;
    }

    private List<AlilogItemConfigProperties> entities;
}

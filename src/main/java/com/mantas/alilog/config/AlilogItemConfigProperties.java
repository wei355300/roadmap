package com.mantas.alilog.config;

import lombok.Data;

@Data
public class AlilogItemConfigProperties {

    private String entity;
    private String desc;
    private String accessId;
    private String accessKey;
    private String host;
    private String query;
}

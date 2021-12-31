package com.mantas.nacos;

import lombok.Data;

@Data
public abstract class NacosConf {
    private String module;
    private String namespace;
    private String serverAddr;
    private String dataId;
    private String groupId;
}

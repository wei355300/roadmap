package com.mantas.nacos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class NacosProperties {
    private String module;
    private String namespace;
    private String serverAddr;
    private String dataId;
    private String groupId;
}

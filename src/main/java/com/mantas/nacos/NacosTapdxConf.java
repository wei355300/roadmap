package com.mantas.nacos;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 从配置文件中获取 tapdx 项目的 nacos 配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "nacos.config.tapdx", ignoreUnknownFields = true)
public class NacosTapdxConf extends NacosConf {

        private String module = "TapdX";
}

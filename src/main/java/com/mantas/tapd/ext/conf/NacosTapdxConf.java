package com.mantas.tapd.ext.conf;

import com.mantas.nacos.NacosConf;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 从配置文件中获取 tapdx 项目的 nacos 配置
 * 用于连锁 nacos 配置服务器
 */
@Data
@ToString
@Component
@ConfigurationProperties(prefix = "nacos.config.tapdx", ignoreUnknownFields = true)
public class NacosTapdxConf extends NacosConf {

        private String module = "TapdX";
}

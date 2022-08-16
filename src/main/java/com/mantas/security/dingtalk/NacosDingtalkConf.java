package com.mantas.security.dingtalk;

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
@ConfigurationProperties(prefix = "nacos.config.dingtalk", ignoreUnknownFields = true)
public class NacosDingtalkConf extends NacosConf {

}

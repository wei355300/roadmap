package com.mantas.zentao.config;

import com.mantas.nacos.NacosProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Component
@ConfigurationProperties(prefix = "nacos.config.zentao", ignoreUnknownFields = true)
public class NacosZentaoProperties extends NacosProperties {

        private String module = "zentao";
}

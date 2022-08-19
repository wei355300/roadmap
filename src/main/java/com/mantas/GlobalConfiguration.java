package com.mantas;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.Set;

@Configuration
@MapperScan(basePackages = "com.mantas.**.mapper")
public class GlobalConfiguration {

//    /**
//     * set JacksonJsonProvider to default Provider(SPI) for JsonPath
//     */
//    static {
//        com.jayway.jsonpath.Configuration.setDefaults(new com.jayway.jsonpath.Configuration.Defaults() {
//
//            private final JsonProvider jsonProvider = new JacksonJsonProvider();
//            private final MappingProvider mappingProvider = new JacksonMappingProvider();
//
//            @Override
//            public JsonProvider jsonProvider() {
//                return jsonProvider;
//            }
//
//            @Override
//            public MappingProvider mappingProvider() {
//                return mappingProvider;
//            }
//
//            @Override
//            public Set<Option> options() {
//                return EnumSet.noneOf(Option.class);
//            }
//        });
//    }

    /**
     * spring.jackson.date-format 的配置无法在 LocalDateTime 上生效,
     * 固通过自定义的方式, 设置 date 的输出格式
     *
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        String dateFormat = "yyyy-MM-dd";
        String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
        return builder -> {
            builder.simpleDateFormat(dateTimeFormat);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
        };
    }
}

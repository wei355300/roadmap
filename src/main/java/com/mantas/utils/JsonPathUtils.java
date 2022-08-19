package com.mantas.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;

public class JsonPathUtils {

    public static final Configuration SnakeCaseConfiguration = defaultSnakeCaseConfiguration();

    private static Configuration defaultSnakeCaseConfiguration() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        objectMapper.registerModule(new JavaTimeModule());

        JsonProvider jsonProvider = new JacksonJsonProvider();

        final MappingProvider mappingProvider = new JacksonMappingProvider(objectMapper);
        return Configuration.builder().jsonProvider(jsonProvider).mappingProvider(mappingProvider).build();
    }

    public static <T> T to(String json, String pattern, TypeRef<T> type) {
//        Configuration conf = Configuration.defaultConfiguration();
        return JsonPath.using(SnakeCaseConfiguration).parse(json).read(pattern, type);
    }

//    public static <T, B> T to(Configuration conf, String json, String pattern, Class clazz) {
//        TypeRef<T> type = new TypeRef() {
//            @Override
//            public Type getType() {
//                return clazz.getGenericSuperclass();
//            }
//        };
//        return JsonPath.using(conf).parse(json).read(pattern, type);
//    }
}

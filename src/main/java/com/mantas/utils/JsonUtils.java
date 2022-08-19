package com.mantas.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Map;
import java.util.Optional;

public class JsonUtils {
    /**
     * fixme
     * 可以通过Bean的方式, 获取 spring 内置的 ObjectMapper,
     * @Bean
     * public OtherBean injectObjectMapper(ObjectMapper objectMapper) {}
     */
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        //Jackson 默认不支持 Java8 的 LocalDateTime,
        //需要在 pom.xml 中添加 jackson-datatype-jsr310 并手动注册 JavaTimeModule
        //注: spring自身支持, 已通过 autoconfiguration 设置好了
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static String prettyJson(String json) throws JsonProcessingException {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
    }

    public static <T> Optional<String> prettyJsonOptional(T object) {
        try {
            String ret = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            return Optional.of(ret);
        } catch (JsonProcessingException e) {
            System.out.println(e);
            return Optional.empty();
        }
    }

    public static <T> String toJson(T obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> Optional<String> toJsonOptional(T obj) {
        try {
            String ret = objectMapper.writeValueAsString(obj);
            return Optional.of(ret);
        } catch (JsonProcessingException e) {
            System.out.println(e);
            return Optional.empty();
        }
    }

    public static <T> T toObj(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

    public static <T> T toObj(String json, TypeReference<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

    public static Map<String, Object> toMap(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Map.class);
    }
}

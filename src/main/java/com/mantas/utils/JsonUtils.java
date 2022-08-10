package com.mantas.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Optional;

public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

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

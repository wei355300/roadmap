package com.mantas.tapd.dto;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TapdOwnerConverterOfJsonDeSerialization extends StdConverter<String, List<String>> {

    /**
     * Main conversion method.
     *
     * @param value
     */
    @Override
    public List<String> convert(String value) {

        if (Objects.isNull(value)) {
            return null;
        }
        if (value.endsWith(";")) {
            value = value.substring(0, value.length() - 1);
        }
        return Arrays.asList(value.split(";"));
    }
}

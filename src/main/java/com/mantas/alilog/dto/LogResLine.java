package com.mantas.alilog.dto;

import lombok.Data;

import java.util.List;

@Data
public class LogResLine {

    @Data
    public static class Column {
        private String label;
        private String value;
    }

    private List<Column> columns;
}

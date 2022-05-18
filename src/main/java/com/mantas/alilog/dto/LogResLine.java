package com.mantas.alilog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.mantas.controller.ResponseJsonView;
import lombok.Data;

import java.util.List;

@JsonView(ResponseJsonView.Public.class)
@Data
public class LogResLine {

    @JsonView(ResponseJsonView.Public.class)
    @Data
    public static class Column {
        private String label;
        private String value;
    }

    private List<Column> columns;
}

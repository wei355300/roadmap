package com.mantas.tapd;

import lombok.Data;

@Data
public class TapdResult<R> {

    private int status;
    private R data;
    private String info;
}

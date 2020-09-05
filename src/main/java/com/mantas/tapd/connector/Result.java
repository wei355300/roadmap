package com.mantas.tapd.connector;

import lombok.Data;

@Data
public class Result<T> {
    int status;
    T data;
}

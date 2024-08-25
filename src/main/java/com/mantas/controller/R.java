package com.mantas.controller;

import lombok.Data;

@Data
public class R<T> {
    private int code;
    private String msg;
    private T data;

    private R() {
        this(RCode.CODE_SUCCESS, RCode.MSG_SUCCESS);
    }

    private R(T data) {
        this(RCode.CODE_SUCCESS, RCode.MSG_SUCCESS, data);
    }

    private R(int code, String msg) {
        this(code, msg, null);
    }

    private R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static R<?> success() {
        return new R<>();
    }

    public static <T> R<T> success(T data) {
        return new R<>(data);
    }

    public static <T> R<T> result(int code, String msg) {
        return new R<>(code, msg);
    }

    public static <T> R<T> result(int code, String msg, T data) {
        return new R<>(code, msg, data);
    }
}

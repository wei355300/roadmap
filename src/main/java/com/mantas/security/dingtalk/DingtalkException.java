package com.mantas.security.dingtalk;

public class DingtalkException extends Exception {
    public DingtalkException(String message) {
        super(message);
    }

    public DingtalkException(String message, Throwable cause) {
        super(message, cause);
    }
}

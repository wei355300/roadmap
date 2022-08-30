package com.mantas.tapd.exception;

public class TapdException extends Exception{
    public TapdException(String message) {
        super(message);
    }
    public TapdException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.mantas.security.account.exception;

import lombok.Data;

@Data
public class AccountNotExistException extends Exception{

    private int errCode = 1000010;

    public AccountNotExistException() {
    }

    public AccountNotExistException(String message, int errCode) {
        super(message);
        this.errCode = errCode;
    }

    public AccountNotExistException(int errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
    }

    public AccountNotExistException(String errMsg, Throwable cause) {
        super(errMsg, cause);
    }

    public AccountNotExistException(int errCode, String errMsg, Throwable cause) {
        super(errMsg, cause);
        this.errCode = errCode;
    }
}

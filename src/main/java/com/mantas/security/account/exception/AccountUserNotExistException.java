package com.mantas.security.account.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AccountUserNotExistException extends Exception{

    private int errCode = 1000012;

    public AccountUserNotExistException() {
    }

    public AccountUserNotExistException(String message) {
        super(message);
    }

    public AccountUserNotExistException(int errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
    }

    public AccountUserNotExistException(String errMsg, Throwable cause) {
        super(errMsg, cause);
    }

    public AccountUserNotExistException(int errCode, String errMsg, Throwable cause) {
        super(errMsg, cause);
        this.errCode = errCode;
    }
}

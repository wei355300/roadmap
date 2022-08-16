package com.mantas.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R exceptionHandler(Exception exception) {
        return R.result(-1, exception.getMessage());
    }
}

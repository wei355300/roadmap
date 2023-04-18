package com.mantas.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class MControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R exceptionHandler(Exception exception) {
        log.error("", exception);
        return R.result(-1, exception.getMessage());
    }
}

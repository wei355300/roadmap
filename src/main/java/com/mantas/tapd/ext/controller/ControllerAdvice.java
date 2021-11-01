package com.mantas.tapd.ext.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public String exceptionHandler(Exception e) {
        System.out.println(e);
        return "error";
    }
}

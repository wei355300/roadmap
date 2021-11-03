package com.mantas.tapd.ext.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return "error";
    }
}

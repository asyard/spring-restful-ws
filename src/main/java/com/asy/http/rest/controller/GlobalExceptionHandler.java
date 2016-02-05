package com.asy.http.rest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by asy
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                 HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
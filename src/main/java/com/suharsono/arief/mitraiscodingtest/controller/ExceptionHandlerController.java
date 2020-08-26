package com.suharsono.arief.mitraiscodingtest.controller;

import com.suharsono.arief.mitraiscodingtest.response.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseEntity requestHandlingBadRequest() {
        return ResponseBuilder.buildResponseBadRequest();
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity requestHandlingError(Exception ex) {
        return ResponseBuilder.buildResponseServerError();
    }
    
}

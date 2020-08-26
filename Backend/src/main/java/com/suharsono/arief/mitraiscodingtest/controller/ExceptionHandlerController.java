package com.suharsono.arief.mitraiscodingtest.controller;

import com.suharsono.arief.mitraiscodingtest.response.ResponseBuilder;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex2 = (MethodArgumentNotValidException) ex;
            List<ObjectError> errors = ex2.getBindingResult().getAllErrors();
            String message = "";
            for (ObjectError error : errors) {
                if (!"".equals(message)) {
                    message += ", ";
                }
                message += error.getDefaultMessage();
            }
            return ResponseBuilder.buildResponseBadRequest(message);
        }
        return ResponseBuilder.buildResponseServerError();
    }
    
}

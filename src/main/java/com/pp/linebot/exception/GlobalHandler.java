package com.pp.linebot.exception;

import com.pp.linebot.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handlerAllException(Exception e) {

        ErrorResponse response = new ErrorResponse();
        response.setCode("INTERNAL SERVER ERROR");
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidateException.class)
    public final ResponseEntity<ErrorResponse> handlerValidate(ValidateException e) {
        ErrorResponse response = new ErrorResponse();
        response.setCode("BAD REQUEST");
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }





}

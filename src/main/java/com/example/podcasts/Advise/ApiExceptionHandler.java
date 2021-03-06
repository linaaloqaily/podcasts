package com.example.podcasts.Advise;


import com.example.podcasts.DTO.API;
import com.example.podcasts.Exception.ApiRequestException;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        API apiExceptions = new API(e.getMessage(), badRequest, ZonedDateTime.now());
        return new ResponseEntity<>(apiExceptions, badRequest);
    }
    @ExceptionHandler(value = {SizeLimitExceededException.class})
    public ResponseEntity<Object> handleApiRequestException(SizeLimitExceededException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        API apiExceptions = new API("Size can't be more than 10MB", badRequest, ZonedDateTime.now());
        return new ResponseEntity<>(apiExceptions, badRequest);
    }

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity handleException(Exception exception) {
//        System.out.println(exception.getMessage());
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
//    }

}
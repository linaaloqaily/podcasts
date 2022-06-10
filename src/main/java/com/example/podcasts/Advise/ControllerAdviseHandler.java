//package com.example.podcasts.Advise;
//
//import com.example.podcasts.DTO.API;
//import com.example.podcasts.Exception.InvalidIdException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//public class ControllerAdviseHandler {
//
//    @ExceptionHandler(value = InvalidIdException.class)
//    public ResponseEntity<API> handleDataIntegrity(InvalidIdException invalidIDException){
//        String message=invalidIDException.getMessage();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API(message,400));
//    }
//
//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<API> handleException(Exception exception) {
//        System.out.println(exception.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API(exception.getMessage(), 400));
//
//    }
//}

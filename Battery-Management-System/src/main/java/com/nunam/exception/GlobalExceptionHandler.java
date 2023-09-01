package com.nunam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VehicleException.class)
    public ResponseEntity<MyErrorDetails> vehicleExceptionHandler(VehicleException ex, WebRequest wb){

        MyErrorDetails err = new MyErrorDetails();
        err.setTime(LocalDateTime.now());
        err.setMessage(ex.getMessage());
        err.setDetails(wb.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BatteryException.class)
    public ResponseEntity<MyErrorDetails> batteryExceptionHandler(BatteryException ex, WebRequest wb){

        MyErrorDetails err = new MyErrorDetails();
        err.setTime(LocalDateTime.now());
        err.setMessage(ex.getMessage());
        err.setDetails(wb.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MyErrorDetails> ValidationExceptionHandler(MethodArgumentNotValidException m){

        MyErrorDetails err = new MyErrorDetails();
        err.setTime(LocalDateTime.now());
        err.setMessage("Validation error!");
        err.setDetails(m.getBindingResult().getFieldError().getDefaultMessage());

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> exceptionHandler(Exception ex, WebRequest wb){

        MyErrorDetails err = new MyErrorDetails();
        err.setTime(LocalDateTime.now());
        err.setMessage(ex.getMessage());
        err.setDetails(wb.getDescription(false));

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

}

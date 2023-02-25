package vougth.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import vougth.api.exception.*;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EventNoContentException.class)
    public ResponseEntity<Object> handleExceptions(EventNoContentException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("No Content");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        return entity;
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(EventNotFoundException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Not found");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(UserNoContentException.class)
    public ResponseEntity<Object> handleExceptions(UserNoContentException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("No Content");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        return entity;
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(TicketNotFoundException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Not found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventNotExistsException.class)
    public ResponseEntity<Object> handleExceptions(EventNotExistsException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("No content");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}

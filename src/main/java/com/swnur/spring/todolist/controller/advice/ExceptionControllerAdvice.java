package com.swnur.spring.todolist.controller.advice;

import com.swnur.spring.todolist.exception.DBOperationException;
import com.swnur.spring.todolist.exception.IllegalTaskCreationDateException;
import com.swnur.spring.todolist.exception.ValidationException;
import com.swnur.spring.todolist.exception.TaskNotFoundException;
import com.swnur.spring.todolist.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDetails> exceptionNotFoundHandler(TaskNotFoundException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDetails);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDetails> exceptionInvalidParameterHandler(ValidationException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

    @ExceptionHandler(IllegalTaskCreationDateException.class)
    public ResponseEntity<ErrorDetails> exceptionIllegalTaskCreateDateHandler(IllegalTaskCreationDateException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorDetails> exceptionSQLHandler(SQLException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

    @ExceptionHandler(DBOperationException.class)
    public ResponseEntity<ErrorDetails> exceptionDBOperationHandler(DBOperationException e) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }
}

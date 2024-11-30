package com.swnur.spring.todolist.exception;

public class DBOperationException extends RuntimeException {
    public DBOperationException(String message) {
        super(message);
    }
}

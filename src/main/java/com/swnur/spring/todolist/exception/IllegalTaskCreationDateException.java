package com.swnur.spring.todolist.exception;

public class IllegalTaskCreationDateException extends RuntimeException{
    public IllegalTaskCreationDateException(String message) {
        super(message);
    }
}

package com.swnur.spring.todolist.util;


import com.swnur.spring.todolist.exception.InvalidParameterException;
import com.swnur.spring.todolist.model.Task;

import java.time.LocalDate;

public class ValidationUtils {

    public static void validateTask(Task task) {
        validateString(task.getHeadline());
        validateString(task.getDescription());
        validateLocalDate(task.getCreationDate());
        validateLocalDate(task.getDueDate());
    }

    private static void validateString(String value) {
        if (value == null || value.isEmpty()) {
            throw new InvalidParameterException("String parameter can not be null or empty");
        }
    }

    private static void validateLocalDate(LocalDate localDate) {
        if (localDate == null) {
            throw new InvalidParameterException("LocalDate parameter can not be null");
        }
    }
}

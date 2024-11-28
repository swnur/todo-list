package com.swnur.spring.todolist.util;

import com.swnur.spring.todolist.exception.ValidationException;
import com.swnur.spring.todolist.model.Task;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class ValidationUtils {

    private ValidationUtils() {}

    public static void validateTaskNotEmpty(Task task) {
        if (Objects.isNull(task))
            throw new ValidationException("'task' is empty");
        if (!StringUtils.hasText(task.getHeadline()))
            throw new ValidationException("'headline' parameter can not be null");
        if (!StringUtils.hasText(task.getDescription()))
            throw new ValidationException("'description' parameter can not be null");
        if (Objects.isNull(task.getCreationDate()))
            throw new ValidationException("'creationDate' parameter can not be null");
        if (Objects.isNull(task.getDueDate()))
            throw new ValidationException("'dueDate' parameter can not be null");
    }

}

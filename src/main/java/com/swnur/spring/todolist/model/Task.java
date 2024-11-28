package com.swnur.spring.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private String headline;
    private String description;
    private LocalDate creationDate;
    private LocalDate dueDate;
    private TaskStatus taskStatus;

}

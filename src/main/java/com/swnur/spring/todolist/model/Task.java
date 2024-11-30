package com.swnur.spring.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    private long id;

    private String headline;
    private String description;
    private LocalDate creationDate;
    private LocalDate dueDate;
    private TaskStatus taskStatus;
}

package com.swnur.spring.todolist.repository;

import com.swnur.spring.todolist.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    void createTask(Task task);

    List<Task> getAllTasks();

    List<Task> getAllTasksFilteredByFinished(Boolean isFinished);

    Optional<Task> updateTask(Integer id,
                              String headline,
                              String description,
                              Boolean isFinished);

    void deleteTask(Integer id);
}

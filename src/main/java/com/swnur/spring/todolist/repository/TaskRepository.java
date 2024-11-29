package com.swnur.spring.todolist.repository;

import com.swnur.spring.todolist.model.Task;
import com.swnur.spring.todolist.model.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    void addTask(Task task);

    List<Task> getAllTasks();

    List<Task> getAllTasksFilteredByTaskStatus(TaskStatus taskStatus);

    Optional<Task> updateTask(Integer id,
                              String headline,
                              String description,
                              TaskStatus taskStatus);

    void deleteTask(Integer id);
}

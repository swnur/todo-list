package com.swnur.spring.todolist.repository;

import com.swnur.spring.todolist.exception.NotFoundException;
import com.swnur.spring.todolist.model.Task;
import com.swnur.spring.todolist.model.TaskStatus;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class LocalMapTaskRepository implements TaskRepository {

    private Integer id;
    private final Map<Integer, Task> data;

    public LocalMapTaskRepository() {
        data = new HashMap<>();
        id = 1;
    }

    @Override
    public void addTask(Task task) {
        Integer currentTaskId = id++;

        data.put(currentTaskId, task);
    }

    @Override
    public List<Task> getAllTasks() {
        return data.values().stream().toList();
    }

    @Override
    public List<Task> getAllTasksFilteredByTaskStatus(TaskStatus taskStatus) {
        return data.values().stream()
                .filter(task -> task.getTaskStatus() == taskStatus)
                .toList();
    }

    @Override
    public Optional<Task> updateTask(Integer id, String headline, String description, TaskStatus taskStatus) {
        if (!data.containsKey(id)) {
            throw new NotFoundException("No such task exists with id: " + id);
        }
        Task task = data.get(id);

        if (headline != null) {
            task.setHeadline(headline);
        }
        if (description != null) {
            task.setDescription(description);
        }
        if (taskStatus != null) {
            task.setTaskStatus(taskStatus);
        }

        data.put(id, task);
        return Optional.of(task);
    }

    @Override
    public void deleteTask(Integer id) {
        if (!data.containsKey(id)) {
            throw new NotFoundException("No such task exists with id: " + id);
        }
        data.remove(id);
    }
}

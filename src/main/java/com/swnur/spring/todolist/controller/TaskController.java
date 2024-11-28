package com.swnur.spring.todolist.controller;

import com.swnur.spring.todolist.exception.NotFoundException;
import com.swnur.spring.todolist.model.Task;
import com.swnur.spring.todolist.model.TaskStatus;
import com.swnur.spring.todolist.repository.TaskRepository;
import com.swnur.spring.todolist.util.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class TaskController {

    private final TaskRepository taskRepository;

    @GetMapping("/todo")
    public ResponseEntity<List<Task>> getTasks(@RequestParam(required = false) TaskStatus taskStatus) {
        List<Task> list = taskStatus == null ? taskRepository.getAllTasks() :
                taskRepository.getAllTasksFilteredByTaskStatus(taskStatus);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @PostMapping("/todo")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        ValidationUtils.validateTaskNotEmpty(task);
        taskRepository.createTask(task);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(task);
    }

    @PatchMapping("/todo")
    public ResponseEntity<Task> updateTask(
            @RequestParam Integer id,
            @RequestParam(required = false) String headline,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) TaskStatus taskStatus) {
        Task task = taskRepository.updateTask(id, headline, description, taskStatus)
                .orElseThrow(() -> new NotFoundException("could not update the task"));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(task);
    }

    @DeleteMapping("/todo")
    public ResponseEntity<String> deleteTask(@RequestParam Integer id) {
        taskRepository.deleteTask(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Successfully deleted task with id: " + id);
    }

}

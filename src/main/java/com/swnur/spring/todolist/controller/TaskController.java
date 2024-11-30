package com.swnur.spring.todolist.controller;

import com.swnur.spring.todolist.model.Task;
import com.swnur.spring.todolist.model.TaskStatus;
import com.swnur.spring.todolist.service.TaskManagerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/todo")
public class TaskController {

    private final TaskManagerService taskManagerService;

    @GetMapping
    public ResponseEntity<List<Task>> findAll(@RequestParam(required = false) TaskStatus taskStatus) {
        List<Task> list = taskManagerService.findAll(taskStatus);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @PostMapping()
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        taskManagerService.createTask(task);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String headline,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) TaskStatus taskStatus) {
        Task task = taskManagerService.updateTask(id, headline, description, taskStatus);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {
        taskManagerService.deleteTask(id);
        return ResponseEntity
                .ok()
                .build();
    }

}

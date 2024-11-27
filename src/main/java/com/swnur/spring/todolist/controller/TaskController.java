package com.swnur.spring.todolist.controller;

import com.swnur.spring.todolist.exception.InvalidParameterException;
import com.swnur.spring.todolist.exception.NotFoundException;
import com.swnur.spring.todolist.model.Task;
import com.swnur.spring.todolist.repository.LocalMapTaskRepository;
import com.swnur.spring.todolist.repository.TaskRepository;
import com.swnur.spring.todolist.util.ValidationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@ResponseBody
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(LocalMapTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/todo")
    public ResponseEntity<List<Task>> getTasks(@RequestParam(required = false) Optional<Boolean> isFinished) {
        if (isFinished.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(taskRepository.getAllTasksFilteredByFinished(isFinished.get()));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskRepository.getAllTasks());
    }

    @PostMapping("/todo")
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        try {
            ValidationUtils.validateTask(task);
            taskRepository.createTask(task);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(task);
        } catch (InvalidParameterException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PatchMapping("/todo")
    public ResponseEntity<?> updateTask(
            @RequestParam Integer id,
            @RequestParam(required = false) String headline,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Boolean isFinished) {
        try {
            Task task = taskRepository.updateTask(id, headline, description, isFinished)
                    .orElseThrow(() -> new NotFoundException("could not update the task"));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(task);
        } catch (NotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (InvalidParameterException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/todo")
    public ResponseEntity<String> deleteTask(@RequestParam Integer id) {
        try {
            taskRepository.deleteTask(id);
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body("Successfully deleted task with id: " + id);
        } catch (NotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

}

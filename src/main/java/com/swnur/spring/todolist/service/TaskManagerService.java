package com.swnur.spring.todolist.service;

import com.swnur.spring.todolist.exception.DBOperationException;
import com.swnur.spring.todolist.exception.TaskNotFoundException;
import com.swnur.spring.todolist.model.PublicHoliday;
import com.swnur.spring.todolist.model.Task;
import com.swnur.spring.todolist.model.TaskStatus;
import com.swnur.spring.todolist.proxy.PublicHolidayProxy;
import com.swnur.spring.todolist.exception.IllegalTaskCreationDateException;
import com.swnur.spring.todolist.repository.TaskRepository;
import com.swnur.spring.todolist.util.ValidationUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.swnur.spring.todolist.util.DateUtils.*;

@Service
@AllArgsConstructor
public class TaskManagerService {

    private final TaskRepository taskRepository;
    private final PublicHolidayProxy publicHolidayProxy;

    public List<Task> findAll(TaskStatus taskStatus) {
        return taskStatus == null ? (List<Task>) taskRepository.findAll() :
                taskRepository.findTasksByTaskStatus(taskStatus);
    }

    public void createTask(Task task) {
        ValidationUtils.validateTaskNotEmpty(task);
        final Integer YEAR = 2024;
        final String LOCATION = "PL";
        List<PublicHoliday> list = publicHolidayProxy.findHolidays(YEAR, LOCATION);
        if (isTaskCreationDateHoliday(task.getCreationDate(), list)) {
            LocalDate newDate = findClosestAvailableDate(task.getCreationDate(), list);
            throw new IllegalTaskCreationDateException("Choose another date - closest available date is " + newDate);
        }
        taskRepository.save(task);
    }

    public Task updateTask(Long id, String headline, String description, TaskStatus taskStatus) {
        int rowsAffected = taskRepository.updateTaskBy(id, headline, description, taskStatus);
        if (rowsAffected == 1) {
            return taskRepository.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException("Could not find the task with id: " + id));
        } else {
            throw new DBOperationException("Failed to update the task");
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}

package com.swnur.spring.todolist.service;

import com.swnur.spring.todolist.exception.NotFoundException;
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
        return taskStatus == null ? taskRepository.getAllTasks() :
                taskRepository.getAllTasksFilteredByTaskStatus(taskStatus);
    }

    public void createTask(Task task) {
        ValidationUtils.validateTaskNotEmpty(task);
        final Integer YEAR = 2024;
        final String LOCATION = "PL";
        List<PublicHoliday> list = publicHolidayProxy.getHolidays(YEAR, LOCATION);
        if (isTaskCreationDateHoliday(task.getCreationDate(), list)) {
            LocalDate newDate = findClosestAvailableDate(task.getCreationDate(), list);
            throw new IllegalTaskCreationDateException("Choose another date - closest available date is " + newDate);
        }
        taskRepository.createTask(task);
    }

    public Task updateTask(Integer id, String headline, String description, TaskStatus taskStatus) {
        return taskRepository.updateTask(id, headline, description, taskStatus)
                .orElseThrow(() -> new NotFoundException("could not update the task"));
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteTask(id);
    }
}

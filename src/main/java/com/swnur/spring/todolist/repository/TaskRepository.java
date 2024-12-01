package com.swnur.spring.todolist.repository;

import com.swnur.spring.todolist.model.Task;
import com.swnur.spring.todolist.model.TaskStatus;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query("SELECT * FROM task WHERE task_status = :taskStatus")
    List<Task> findTasksByTaskStatus(TaskStatus taskStatus);

    @Modifying
    @Query("UPDATE task SET headline = :headline, description = :description, task_status = :taskStatus WHERE id = :id")
    int updateTaskBy(Long id,
                        String headline,
                        String description,
                        TaskStatus taskStatus);
}

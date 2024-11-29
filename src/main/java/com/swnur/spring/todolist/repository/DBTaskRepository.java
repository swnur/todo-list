package com.swnur.spring.todolist.repository;

import com.swnur.spring.todolist.exception.DBOperationException;
import com.swnur.spring.todolist.model.Task;
import com.swnur.spring.todolist.model.TaskStatus;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@AllArgsConstructor
public class DBTaskRepository implements TaskRepository {

    private final JdbcTemplate jdbc;

    @Override
    public List<Task> getAllTasks() {
        String sqlQuery = "SELECT * FROM task;";

        return jdbc.query(sqlQuery, new TaskRowMapper());
    }

    @Override
    public List<Task> getAllTasksFilteredByTaskStatus(TaskStatus taskStatus) {
        String sqlQuery = "SELECT * FROM task WHERE task_status = ?;";

        return jdbc.query(sqlQuery, new TaskRowMapper(), taskStatus.name());
    }

    @Override
    public void addTask(Task task) {
        String sqlQuery = "INSERT INTO " +
                                "task(headline, description, creation_date, due_date, task_status) " +
                                "VALUES(?, ?, ?, ?, ?)";

        jdbc.update(sqlQuery,
                task.getHeadline(),
                task.getDescription(),
                task.getCreationDate(),
                task.getDueDate(),
                task.getTaskStatus().name());
    }

    @Override
    public void deleteTask(Integer id) {
        String sqlQuery = "DELETE FROM task WHERE id = ?";

        int rowsAffected = jdbc.update(sqlQuery,
                                        id);

        if (rowsAffected == 0) {
            throw new DBOperationException("Failed to delete the task with id: " + id);
        }
    }

    @Override
    public Optional<Task> updateTask(Integer id, String headline, String description, TaskStatus taskStatus) {
        String sqlQuery = "UPDATE task " +
                          "SET headline = ?, description = ?, task_status = ?" +
                          "WHERE id = ?";

        int rowsAffected = jdbc.update(sqlQuery,
                                    headline,
                                    description,
                                    taskStatus,
                                    id);

        if (rowsAffected == 0) {
            throw new DBOperationException("Failed to update the task with id: " + id);
        }

        return findTaskById(id);
    }

    private Optional<Task> findTaskById(Integer id) {
        String sqlQuery = "SELECT * FROM task WHERE id = ?";
        return jdbc.query(sqlQuery, new TaskRowMapper(), id).stream().findFirst();
    }
}

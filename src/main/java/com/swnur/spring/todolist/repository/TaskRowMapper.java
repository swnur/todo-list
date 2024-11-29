package com.swnur.spring.todolist.repository;

import com.swnur.spring.todolist.model.Task;
import com.swnur.spring.todolist.model.TaskStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();

        task.setHeadline(rs.getString("headline"));
        task.setDescription(rs.getString("description"));
        task.setCreationDate(rs.getDate("creation_date").toLocalDate());
        task.setDueDate(rs.getDate("due_date").toLocalDate());
        task.setTaskStatus(TaskStatus.valueOf(rs.getString("task_status")));

        return task;
    }
}

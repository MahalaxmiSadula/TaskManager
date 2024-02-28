package com.code.taskservice.service;

import com.code.taskservice.dao.Task;
import com.code.taskservice.model.TaskRequest;

import java.util.List;

public interface TaskService {
    Task createTask(TaskRequest taskRequest);

    Task getTask(Long id);

    List<Task> getTasks();

    Task updateTask(Long id, TaskRequest taskRequest);

    void deleteTask(Long id);
}
package com.code.taskservice.repository;

import com.code.taskservice.dao.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByIsCompleted(boolean isCompleted);
}
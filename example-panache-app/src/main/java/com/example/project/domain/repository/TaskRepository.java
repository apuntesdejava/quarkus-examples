package com.example.project.domain.repository;

import com.example.project.domain.model.Task;

public interface TaskRepository {
    Task persist(Task task);
}

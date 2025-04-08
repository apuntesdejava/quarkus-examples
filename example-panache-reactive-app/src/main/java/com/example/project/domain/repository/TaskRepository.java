package com.example.project.domain.repository;

import com.example.project.domain.model.Task;
import io.smallrye.mutiny.Uni;

public interface TaskRepository {
    Uni<Task> persist(Task task);
}

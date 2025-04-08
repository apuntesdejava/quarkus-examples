package com.example.project.domain.repository;

import com.example.project.domain.model.Task;

/**
 * Repository interface for managing Task entities.
 */
public interface TaskRepository {
    /**
     * Persists a Task entity in the repository.
     * @param task the Task entity to persist
     * @return the persisted Task entity
     */
    Task persist(Task task);
}

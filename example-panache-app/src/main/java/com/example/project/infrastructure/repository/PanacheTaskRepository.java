package com.example.project.infrastructure.repository;

import com.example.project.domain.model.Task;
import com.example.project.domain.repository.TaskRepository;
import com.example.project.infrastructure.entity.TaskEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@ApplicationScoped
@Named("PanacheTaskRepository")
public class PanacheTaskRepository implements TaskRepository {

    @Inject
    PanacheTaskEntityRepository repository;

    @Override
    public Task persist(Task task) {
        var taskEntity = TaskEntity.from(task);
        repository.persist(taskEntity);
        return Task.fromEntity(taskEntity);
    }
}

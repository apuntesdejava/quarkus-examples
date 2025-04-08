package com.example.project.infraestructure.repository;

import com.example.project.domain.model.Task;
import com.example.project.domain.repository.TaskRepository;
import com.example.project.infrastructure.entity.TaskEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Named("JpaTaskRepository")
public class JpaTaskRepository implements TaskRepository {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public Task persist(Task task) {
        var taskEntity = TaskEntity.from(task);
        em.persist(taskEntity);
        return Task.fromEntity(taskEntity);
    }
}

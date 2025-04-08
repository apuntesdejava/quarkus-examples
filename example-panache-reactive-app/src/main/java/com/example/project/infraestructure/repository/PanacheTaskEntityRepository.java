package com.example.project.infraestructure.repository;

import com.example.project.infrastructure.entity.TaskEntity;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheTaskEntityRepository implements PanacheRepositoryBase<TaskEntity, Long> {
}

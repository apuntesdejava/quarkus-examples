package com.example.project.infrastructure.repository.panache;

import com.example.project.infrastructure.entity.TaskEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheTaskEntityRepository implements PanacheRepositoryBase<TaskEntity, Long> {
}

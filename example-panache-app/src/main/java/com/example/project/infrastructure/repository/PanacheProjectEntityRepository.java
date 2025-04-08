package com.example.project.infrastructure.repository;

import com.example.project.infrastructure.entity.ProjectEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheProjectEntityRepository implements PanacheRepositoryBase<ProjectEntity,Long> {

    public PanacheQuery<ProjectEntity> findAllWithTasks() {
        return find("select p from ProjectEntity p left join fetch p.tasks");
    }
}

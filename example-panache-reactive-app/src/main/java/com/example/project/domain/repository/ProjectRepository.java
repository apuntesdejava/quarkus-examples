package com.example.project.domain.repository;

import com.example.project.domain.model.Project;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface ProjectRepository {
    Uni<Project> persist(Project project);

    Uni<Project> findByIdOptional(Long id);

    Uni<List<Project>> findAll();
}

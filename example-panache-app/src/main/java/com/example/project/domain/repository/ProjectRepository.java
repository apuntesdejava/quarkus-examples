package com.example.project.domain.repository;

import com.example.project.domain.model.Project;

import java.util.Optional;
import java.util.stream.Stream;

public interface ProjectRepository {
    Project persist(Project project);

    Optional<Project> findByIdOptional(Long id);

    Stream<Project> findAll();
}

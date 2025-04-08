package com.example.project.domain.repository;

import com.example.project.domain.model.Project;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Repository interface for managing operations related to the {@link Project} entity.
 * Provides methods to persist, retrieve, and list projects.
 */
public interface ProjectRepository {

    /**
     * Persists a project in the repository.
     *
     * @param project the project to persist.
     * @return the persisted project.
     */
    Project persist(Project project);

    /**
     * Finds a project by its identifier.
     *
     * @param id the identifier of the project.
     * @return an {@link Optional} containing the project if found, or empty if not.
     */
    Optional<Project> findByIdOptional(Long id);

    /**
     * Retrieves all projects from the repository as a stream.
     *
     * @return a {@link Stream} containing all projects.
     */
    Stream<Project> findAll();
}

package com.example.project.domain.service;

import com.example.project.domain.model.Project;
import com.example.project.domain.repository.ProjectRepository;
import com.example.project.request.ProjectRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Service class for managing projects.
 */
@ApplicationScoped
public class ProjectService {

    private final ProjectRepository projectRepository;

    /**
     * Constructor for ProjectService.
     *
     * @param projectRepository the repository for managing project entities
     */
    @Inject
    public ProjectService(
        @Named("JpaProjectRepository")
        ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    /**
     * Creates a new project based on the provided project request.
     *
     * @param projectRequest the request containing project details
     * @return the created project
     */
    @Transactional
    public Project createProject(ProjectRequest projectRequest) {
        Project project = Project.from(projectRequest);
        return projectRepository.persist(project);
    }

    /**
     * Finds a project by its identifier.
     *
     * @param id the identifier of the project
     * @return an Optional containing the project if found, or empty if not
     */
    public Optional<Project> findByIdOptional(Long id) {
        return projectRepository.findByIdOptional(id);
    }

    /**
     * Retrieves all projects from the repository.
     *
     * @return a stream of all projects
     */
    public Stream<Project> findAllProjects() {
        return projectRepository.findAll();
    }
}

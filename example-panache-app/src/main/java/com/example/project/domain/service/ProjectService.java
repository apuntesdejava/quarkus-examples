package com.example.project.domain.service;

import com.example.project.domain.model.Project;
import com.example.project.domain.repository.ProjectRepository;
import com.example.project.request.ProjectRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Inject
    public ProjectService(
        @Named("JpaProjectRepository")
        ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(ProjectRequest projectRequest) {
        Project project = Project.from(projectRequest);
        return projectRepository.persist(project);
    }

    public Optional<Project> findByIdOptional(Long id) {
        return projectRepository.findByIdOptional(id);
    }

    public Stream<Project> findAllProjects() {
        return projectRepository.findAll();
    }
}

package com.example.project.domain.service;

import com.example.project.domain.model.Project;
import com.example.project.domain.repository.ProjectRepository;
import com.example.project.request.ProjectRequest;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@ApplicationScoped
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Inject
    public ProjectService(
        @Named("PanacheProjectRepository")
        ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Uni<Project> createProject(ProjectRequest projectRequest) {
        Project project = Project.from(projectRequest);
        return projectRepository.persist(project);
    }

    public Uni<Project> findByIdOptional(Long id) {
        return projectRepository.findByIdOptional(id);
    }

    public Uni<List<Project>> findAllProjects() {
        return projectRepository.findAll();
    }
}

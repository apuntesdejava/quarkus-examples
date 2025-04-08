package com.example.project.infrastructure.repository;

import com.example.project.domain.model.Project;
import com.example.project.domain.repository.ProjectRepository;
import com.example.project.infrastructure.entity.ProjectEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
@Named("PanacheProjectRepository")
public class PanacheProjectRepository implements ProjectRepository  {

    @Inject
    PanacheProjectEntityRepository repository;

    @Override
    public Project persist(Project project) {
        var projectEntity = ProjectEntity.from(project);
        repository.persist(projectEntity);
        return Project.fromEntity(projectEntity);
    }

    @Override
    public Optional<Project> findByIdOptional(Long id) {
        return repository
            .findByIdOptional(id)
            .map(Project::fromEntity);
    }

    @Override
    public Stream<Project> findAll() {
        return repository.findAll()
            .stream()
            .map(Project::fromEntity);
    }

}

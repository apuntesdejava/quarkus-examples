package com.example.project.infraestructure.repository;

import com.example.project.domain.model.Project;
import com.example.project.domain.model.Task;
import com.example.project.domain.repository.ProjectRepository;
import com.example.project.infrastructure.entity.ProjectEntity;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@ApplicationScoped
@Named("PanacheProjectRepository")
public class PanacheProjectRepository implements ProjectRepository {

    @Inject
    PanacheProjectEntityRepository repository;

    @Override
    public Uni<Project> persist(Project project) {
        var projectEntity = ProjectEntity.from(project);
        return repository.persist(projectEntity).map(Project::fromEntity);
    }

    @Override
    public Uni<Project> findByIdOptional(Long id) {
        return repository
            .findById(id)
            .onItem().ifNotNull().transform(Project::fromEntity);
    }

    @Override
    public Uni<List<Project>> findAll() {
        return repository.findAllWithTasks()
                         .list()
                         .map(projectEntities -> projectEntities
                             .stream()
                             .map(project -> Project
                                 .fromEntityWithTasks(project, project.getTasks()
                                                                      .stream()
                                                                      .map(Task::fromEntity)
                                                                      .toList())
                             )
                             .toList());
    }

}

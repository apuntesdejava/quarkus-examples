package com.example.project.infrastructure.repository;

import com.example.project.domain.model.Project;
import com.example.project.domain.repository.ProjectRepository;
import com.example.project.infrastructure.entity.ProjectEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
@Named("JpaProjectRepository")
public class JpaProjectRepository implements ProjectRepository {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public Project persist(Project project) {
        var projectEntity = ProjectEntity.from(project);
        em.persist(projectEntity);
        return Project.fromEntity(projectEntity);
    }

    @Override
    public Optional<Project> findByIdOptional(Long id) {
        return Optional.ofNullable( em.find(ProjectEntity.class, id))
            .map(Project::fromEntity);
    }

    @Override
    public Stream<Project> findAll() {
        var query = em.createQuery("SELECT p FROM ProjectEntity p", ProjectEntity.class);
        return query.getResultStream()
            .map(Project::fromEntity);
    }
}

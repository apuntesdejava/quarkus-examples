package com.example.project.infrastructure.repository.jpa;

import com.example.project.domain.model.Project;
import com.example.project.domain.repository.ProjectRepository;
import com.example.project.infrastructure.entity.ProjectEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.JoinType;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Repository implementation for managing operations related to the {@link Project} entity.
 * Provides methods to persist, retrieve, and list projects.
 */
@ApplicationScoped
@Named("JpaProjectRepository")
public class JpaProjectRepository implements ProjectRepository {

    @Inject
    EntityManager em;

    /**
     * Persists a project in the repository.
     *
     * @param project the project to persist.
     * @return the persisted project.
     */
    @Override
    @Transactional
    public Project persist(Project project) {
        var projectEntity = ProjectEntity.from(project);
        em.persist(projectEntity);
        return Project.fromEntity(projectEntity);
    }

    /**
     * Finds a project by its identifier.
     *
     * @param id the identifier of the project.
     * @return an {@link Optional} containing the project if found, or empty if not.
     */
    @Override
    public Optional<Project> findByIdOptional(Long id) {
        return Optional.ofNullable(em.find(ProjectEntity.class, id))
                       .map(Project::fromEntity);
    }

    /**
     * Retrieves all projects from the repository as a stream.
     *
     * @return a {@link Stream} containing all projects.
     */
    @Override
    public Stream<Project> findAll() {
        // Using JPQL to fetch all projects with their tasks
        /*var query = em.createQuery("SELECT p FROM ProjectEntity p left join fetch p.tasks", ProjectEntity.class);
        return query.getResultStream()
                    .map(Project::fromEntity);*/

        // Using Criteria API to fetch all projects with their tasks
        var cb = em.getCriteriaBuilder();
        var cq = cb.createQuery(ProjectEntity.class);
        var root = cq.from(ProjectEntity.class);
        cq.select(root).distinct(true);
        root.fetch("tasks", JoinType.LEFT);
        var query = em.createQuery(cq);
        return query.getResultStream()
                    .map(Project::fromEntityWithTasks);

    }
}

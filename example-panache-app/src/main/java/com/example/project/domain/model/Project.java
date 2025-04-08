package com.example.project.domain.model;

import com.example.project.infrastructure.entity.ProjectEntity;
import com.example.project.request.ProjectRequest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents a project with its details.
 *
 * @param id        the unique identifier of the project
 * @param name      the name of the project
 * @param startDate the start date of the project
 * @param endDate   the end date of the project
 * @param tasks     the list of tasks associated with the project
 */
public record Project(
    Long id,
    String name,
    LocalDate startDate,
    LocalDate endDate,
    List<Task> tasks
) {

    /**
     * Creates a new `Project` instance from a `ProjectRequest` object.
     *
     * @param projectRequest the `ProjectRequest` containing the data for the new `Project`
     * @return a new `Project` instance with the provided data and an empty list of tasks
     */
    public static Project from(ProjectRequest projectRequest) {
        return new Project(
            null,
            projectRequest.name(),
            projectRequest.startDate(),
            projectRequest.endDate(),
            Collections.emptyList()
        );
    }

    /**
     * Converts a `ProjectEntity` object to a `Project` object.
     *
     * @param projectEntity the `ProjectEntity` to be converted
     * @return a `Project` object containing the data from the given `ProjectEntity`
     */
    public static Project fromEntity(ProjectEntity projectEntity) {
        return new Project(
            projectEntity.getId(),
            projectEntity.getName(),
            projectEntity.getStartDate(),
            projectEntity.getEndDate(),
            Collections.emptyList()
        );
    }

    public static Project fromEntityWithTasks(ProjectEntity projectEntity) {
        return new Project(
            projectEntity.getId(),
            projectEntity.getName(),
            projectEntity.getStartDate(),
            projectEntity.getEndDate(),
            Optional.ofNullable(projectEntity.getTasks())
                    .map(tasks -> tasks
                        .stream()
                        .map(Task::fromEntity)
                        .toList())
                    .orElse(Collections.emptyList())
        );
    }

}

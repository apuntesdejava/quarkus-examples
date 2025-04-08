package com.example.project.domain.model;

import com.example.project.infrastructure.entity.TaskEntity;
import com.example.project.request.TaskRequest;
import com.example.project.type.TaskStatus;

/**
 * Represents a task with an ID, name, description, associated project, and status.
 *
 * @param id          the unique identifier of the task
 * @param name        the name of the task
 * @param description a brief description of the task
 * @param status      the current status of the task
 */
public record Task(Long id,
                   String name,
                   String description,
                   Project project,
                   TaskStatus status) {


    /**
     * Creates a new `Task` instance from a `TaskRequest` object.
     *
     * @param taskRequest the `TaskRequest` containing the data for the new `Task`
     * @return a new `Task` instance with the provided data
     */
    public static Task from(TaskRequest taskRequest) {
        return new Task(
            null,
            taskRequest.name(),
            taskRequest.description(),
            null,
            TaskStatus.from(taskRequest.status())
        );
    }

    /**
     * Creates a new `Task` instance from a `TaskRequest` object and a `Project` object.
     *
     * @param taskRequest the `TaskRequest` containing the data for the new `Task`
     * @param project     the `Project` associated with the new `Task`
     * @return a new `Task` instance with the provided data
     */
    public static Task from(TaskRequest taskRequest, Project project) {
        return new Task(
            null,
            taskRequest.name(),
            taskRequest.description(),
            project,
            TaskStatus.from(taskRequest.status())
        );
    }

    /**
     * Converts a `TaskEntity` object to a `Task` object.
     *
     * @param taskEntity the `TaskEntity` to be converted
     * @return a `Task` object containing the data from the given `TaskEntity`
     */
    public static Task fromEntity(TaskEntity taskEntity) {
        return new Task(
            taskEntity.getId(),
            taskEntity.getName(),
            taskEntity.getDescription(),
            Project.fromEntity(taskEntity.getProject()),
            taskEntity.getStatus()
        );

    }
}

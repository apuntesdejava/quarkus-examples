package com.example.project.domain.model;

import com.example.project.infrastructure.entity.TaskEntity;
import com.example.project.request.TaskRequest;
import com.example.project.type.TaskStatus;

public record Task(Long id,
                   String name,
                   String description,
                   Project project,
                   TaskStatus status) {
    public static Task from(TaskRequest taskRequest) {
        return new Task(
            null,
            taskRequest.name(),
            taskRequest.description(),
            null,
            TaskStatus.from(taskRequest.status())
        );
    }

    public static Task from(TaskRequest taskRequest,Project project) {
        return new Task(
            null,
            taskRequest.name(),
            taskRequest.description(),
            project,
            TaskStatus.from(taskRequest.status())
        );
    }

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

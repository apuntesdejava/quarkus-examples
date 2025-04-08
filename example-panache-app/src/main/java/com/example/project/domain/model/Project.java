package com.example.project.domain.model;

import com.example.project.infrastructure.entity.ProjectEntity;
import com.example.project.request.ProjectRequest;

import java.time.LocalDate;
import java.util.List;

public record Project(
    Long id,
    String name,
    LocalDate startDate,
    LocalDate endDate,
    List<Task> tasks
) {
    public static Project from(ProjectRequest projectRequest) {
        return new Project(
            null,
            projectRequest.name(),
            projectRequest.startDate(),
            projectRequest.endDate(),
            null
        );
    }

    public static Project fromEntity(ProjectEntity projectEntity) {
        return new Project(
            projectEntity.getId(),
            projectEntity.getName(),
            projectEntity.getStartDate(),
            projectEntity.getEndDate(),
            null
        );
    }

    public static Project fromEntity(ProjectEntity projectEntity, List<Task> tasks) {
        return new Project(
            projectEntity.getId(),
            projectEntity.getName(),
            projectEntity.getStartDate(),
            projectEntity.getEndDate(),
            tasks
        );
    }
}

package com.example.project.infrastructure.entity;

import com.example.project.domain.model.Task;
import com.example.project.type.TaskStatus;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public static TaskEntity from(Task task) {
        var entity = new TaskEntity();
        entity.setId(task.id());
        entity.setName(task.name());
        entity.setDescription(task.description());
        entity.setProject(ProjectEntity.from(task.project()));
        entity.setStatus(task.status());
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}

package com.example.project.domain.service;

import com.example.project.domain.model.Task;
import com.example.project.domain.repository.ProjectRepository;
import com.example.project.domain.repository.TaskRepository;
import com.example.project.request.TaskRequest;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@ApplicationScoped
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Inject
    public TaskService(
        @Named("PanacheTaskRepository") TaskRepository taskRepository,
        @Named("PanacheProjectRepository") ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public Uni<Task> createTask(TaskRequest taskRequest) {
        return projectRepository.findByIdOptional(taskRequest.projectId())
                                .flatMap(project -> {
                                    var task = Task.from(taskRequest, project);
                                    return taskRepository.persist(task);
                                });
    }

}

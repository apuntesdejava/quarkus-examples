package com.example.project.domain.service;

import com.example.project.domain.model.Task;
import com.example.project.domain.repository.ProjectRepository;
import com.example.project.domain.repository.TaskRepository;
import com.example.project.request.TaskRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@ApplicationScoped
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Inject
    public TaskService(
        @Named("JpaTaskRepository") TaskRepository taskRepository,
        @Named("JpaProjectRepository") ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public Task createTask(TaskRequest taskRequest) {

        return projectRepository.findByIdOptional(taskRequest.projectId())
                                .map(project -> {
                                    var task = Task.from(taskRequest, project);
                                    return taskRepository.persist(task);
                                })
                                .orElse(null);
    }

}

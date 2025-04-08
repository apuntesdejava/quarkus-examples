package com.example.project.domain.service;

import com.example.project.domain.model.Project;
import com.example.project.domain.model.Task;
import com.example.project.domain.repository.ProjectRepository;
import com.example.project.domain.repository.TaskRepository;
import com.example.project.request.TaskRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

/**
 * Service class for managing tasks.
 */
@ApplicationScoped
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    /**
     * Constructor for TaskService.
     *
     * @param taskRepository the repository for managing task entities
     * @param projectRepository the repository for managing project entities
     */
    @Inject
    public TaskService(
        @Named("PanacheTaskRepository") TaskRepository taskRepository,
        @Named("PanacheProjectRepository") ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    /**
     * Creates a new task based on the provided task request.
     *
     * @param taskRequest the request containing task details
     * @return the created task
     */
    @Transactional
    public Task createTask(TaskRequest taskRequest) {
        return projectRepository
            .findByIdOptional(taskRequest.projectId())
            .map(project -> taskMapper(taskRequest, project))
            .orElse(null);
    }


    /**
     * Maps a TaskRequest to a Task entity and persists it.
     *
     * @param taskRequest the request containing task details
     * @param project the project to which the task belongs
     * @return the persisted task
     */
    private Task taskMapper(TaskRequest taskRequest, Project project) {
        var task = Task.from(taskRequest, project);
        return taskRepository.persist(task);
    }

}

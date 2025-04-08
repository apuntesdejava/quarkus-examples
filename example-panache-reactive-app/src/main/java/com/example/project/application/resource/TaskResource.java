package com.example.project.application.resource;

import com.example.project.domain.service.TaskService;
import com.example.project.request.TaskRequest;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.net.URI;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("tasks")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class TaskResource {

    @Inject
    TaskService taskService;

    @POST
    public Uni<Response> createTask(TaskRequest taskRequest) {
        return taskService.createTask(taskRequest)
                          .onItem()
                          .transform(task -> Response
                              .created(URI.create("tasks/" + task.id()))
                              .entity(task)
                              .build());
    }
}

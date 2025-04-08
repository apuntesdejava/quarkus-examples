package com.example.project.application.resource;

import com.example.project.domain.model.Project;
import com.example.project.domain.service.ProjectService;
import com.example.project.request.ProjectRequest;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("projects")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ProjectResource {

    @Inject
    ProjectService projectService;

    @POST
    @WithTransaction
    public Uni<Response> createProject(ProjectRequest projectRequest) {
        return projectService.createProject(projectRequest)
                             .onItem()
                             .transform(project -> Response
                                 .created(URI.create("projects/" + project.id()))
                                 .entity(project)
                                 .build());
    }

    @GET
    @Path("{id}")
    @WithTransaction
    public Uni<Response> findById(@PathParam("id") Long id) {
        return projectService.findByIdOptional(id)
                             .onItem().ifNotNull()
                             .transform(project -> Response.ok(project).build())
                             .onItem().ifNull()
                             .failWith(
                                 () -> new WebApplicationException("Project not found", Response.Status.NOT_FOUND));
    }

    @GET
    @WithTransaction
    public Uni<List<Project>> findAll() {
        return projectService.findAllProjects();
    }
}

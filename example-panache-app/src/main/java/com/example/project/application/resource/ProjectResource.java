package com.example.project.application.resource;

import com.example.project.domain.service.ProjectService;
import com.example.project.request.ProjectRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.URI;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("projects")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ProjectResource {

    @Inject
    ProjectService projectService;

    @POST
    public Response createProject(ProjectRequest projectRequest) {
        var project = projectService.createProject(projectRequest);
        return Response
            .created(URI.create("projects/" + project.id()))
            .entity(project)
            .build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") Long id) {
        return projectService
            .findByIdOptional(id)
            .map(project -> Response
                .status(Response.Status.OK)
                .entity(project)
            ).orElse(
                Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Project not found")
            ).build();
    }

    @GET
    public Response findAll() {
        var projects = projectService.findAllProjects().toList();
        return Response
            .status(Response.Status.OK)
            .entity(projects)
            .build();
    }
}

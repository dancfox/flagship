package com.barinek.flagship.projects;

import com.google.inject.Inject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectController {
    private ProjectDAO dao;

    @Inject
    public ProjectController(ProjectDAO dao) {
        this.dao = dao;
    }

    @GET
    public List<Project> getProjects() {
        return dao.getProjects();
    }
}


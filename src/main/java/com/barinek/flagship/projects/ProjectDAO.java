package com.barinek.flagship.projects;

import com.google.inject.Inject;

import java.util.List;

public class ProjectDAO {
    private ProjectMapper mapper;

    @Inject
    public ProjectDAO(ProjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<Project> getProjects() {
        return mapper.getProjects();
    }
}

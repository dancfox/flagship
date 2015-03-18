package com.barinek.flagship;

import com.barinek.flagship.projects.Project;
import com.barinek.flagship.projects.ProjectDAO;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class ProjectDAOTest {
    @Test
    public void getProjects() throws IOException {
        Injector injector = Guice.createInjector(new Environment("test"), new DataSourceModule());

        ProjectDAO dao = injector.getInstance(ProjectDAO.class);
        List<Project> projects = dao.getProjects();
        assertEquals(2, projects.size());
        assertEquals("Project 1", projects.get(0).getName());
        assertEquals("Project 2", projects.get(1).getName());
    }
}

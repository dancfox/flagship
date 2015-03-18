package com.barinek.flagship.projects;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProjectMapper {

    @Select("select id, name from projects order by name asc")
        // ensures order for testing
    List<Project> getProjects();
}

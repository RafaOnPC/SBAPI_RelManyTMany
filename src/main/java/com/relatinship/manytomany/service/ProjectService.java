package com.relatinship.manytomany.service;

import com.relatinship.manytomany.model.Project;

import java.util.List;

public interface ProjectService {

    public List<Project> getProjects();

    public Project getProject(Long projectId);

    public Project createProject(Project project);

    public Project updateProject(Long projectId, Project project);

    public Project deleteProject(Long projectId);
}

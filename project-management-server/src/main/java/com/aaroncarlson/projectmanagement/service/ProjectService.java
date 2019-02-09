package com.aaroncarlson.projectmanagement.service;

import com.aaroncarlson.projectmanagement.exception.CustomException;
import com.aaroncarlson.projectmanagement.model.Backlog;
import com.aaroncarlson.projectmanagement.model.Project;
import com.aaroncarlson.projectmanagement.repository.BacklogRepository;
import com.aaroncarlson.projectmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveOrUpdateProject(Project project) {
        String identifier = project.getIdentifier().toUpperCase();

        // Logic to determine ownership when updating
        try {
            if (project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setIdentifier(identifier);
            }
            if (project.getId() != null) {
                project.setBacklog(backlogRepository.findByIdentifier(identifier));
            }
            return projectRepository.save(project);
        } catch (Exception exception) {
            throw new CustomException("Identifier '" + identifier + "' must be unique");
        }
    }

    public Project findByIdentifier(String identifier) {
        Project project = projectRepository.findByIdentifier(identifier.toUpperCase());

        if (project == null) {
            throw new CustomException("Identifier '" + identifier + "' does not exist");
        }

        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String identifier) {
        Project project = projectRepository.findByIdentifier(identifier.toUpperCase());

        if (project == null) {
            throw new CustomException("Could not delete Project, identifier '" + identifier + "' does not exist");
        }

        projectRepository.delete(project);
    }
}

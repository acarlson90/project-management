package com.aaroncarlson.projectmanagement.service;

import com.aaroncarlson.projectmanagement.exception.CustomException;
import com.aaroncarlson.projectmanagement.model.Sprint;
import com.aaroncarlson.projectmanagement.model.Project;
import com.aaroncarlson.projectmanagement.repository.SprintRepository;
import com.aaroncarlson.projectmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private SprintRepository sprintRepository;

    public Project saveOrUpdateProject(Project project) {
        String identifier = project.getIdentifier().toUpperCase();

        // Logic to determine ownership when updating
        try {
            if (project.getId() == null) {
                Sprint sprint = new Sprint();
                sprint.setName("Backlog");
                project.setSprint(sprint);
                sprint.setProject(project);
            }
            if (project.getId() != null) {
                project.setSprint(sprintRepository.findByProject(project));
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
        Project project = findByIdentifier(identifier);

        projectRepository.delete(project);
    }
}

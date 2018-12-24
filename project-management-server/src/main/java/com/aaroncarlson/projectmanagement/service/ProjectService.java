package com.aaroncarlson.projectmanagement.service;

import com.aaroncarlson.projectmanagement.exception.CustomException;
import com.aaroncarlson.projectmanagement.model.Project;
import com.aaroncarlson.projectmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {

        // Logic to determine ownership when updating
        try {
            project.setIdentifier(project.getIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception exception) {
            throw new CustomException("Identifier '" + project.getIdentifier().toUpperCase() + "' must be unique");
        }
    }
}

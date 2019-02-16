package com.aaroncarlson.projectmanagement.service;

import com.aaroncarlson.projectmanagement.exception.CustomException;
import com.aaroncarlson.projectmanagement.model.Project;
import com.aaroncarlson.projectmanagement.model.Sprint;
import com.aaroncarlson.projectmanagement.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SprintService {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private SprintRepository sprintRepository;

    public Sprint findProjectByIdentifier(String identifier) {
        Project project = projectService.findByIdentifier(identifier);
        Sprint sprint = findByProject(project);

        return  sprint;
    }

    public Sprint findByProject(Project project) {
        Sprint sprint = sprintRepository.findByProject(project);

        if (sprint == null) {
            throw new CustomException("No Sprint(s) exist for " + project.getIdentifier());
        }

        return sprint;
    }
}

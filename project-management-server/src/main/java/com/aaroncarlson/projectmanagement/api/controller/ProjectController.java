package com.aaroncarlson.projectmanagement.api.controller;

import com.aaroncarlson.projectmanagement.model.Project;
import com.aaroncarlson.projectmanagement.service.ProjectService;
import com.aaroncarlson.projectmanagement.service.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    ValidationErrorService validationErrorService;

    /*
     * @Valid ensures the method receives a valid @RequestBody of type Project
     * @BindingResult - Spring object that hols the results of the validation (@Valid) and binding. The object contains
     * errors that may have occurred. BindingResutl must come after the model object (Project) that is validated or else
     * Spring will fail. When Spring sess @Valid, it tries to find the validator for the object being validated. Spring
     * then invokes the validator and puts any errors in the BindingResult and adds the BindingResult to vhe view model.
     */
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> errorResponseEntity = validationErrorService.getErrorResponseEntity(result);
        if (errorResponseEntity != null) {
            return errorResponseEntity;
        } else {
            projectService.saveOrUpdateProject(project);
            return new ResponseEntity<>(project, HttpStatus.CREATED);
        }
    }

    @GetMapping("")
    public Iterable<?> getAllProjects() {
        return projectService.findAllProjects();
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<?> getProjectByIdentifier(@PathVariable String identifier) {
        Project project = projectService.findByIdentifier(identifier);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{identifier}")
    public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String identifier) {
        projectService.deleteProjectByIdentifier(identifier);

        return new ResponseEntity<>("Project with Identifier '" + identifier +
                "' was successfully deleted", HttpStatus.OK);
    }
}

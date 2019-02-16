package com.aaroncarlson.projectmanagement.api.controller;

import com.aaroncarlson.projectmanagement.model.Task;
import com.aaroncarlson.projectmanagement.service.TaskService;
import com.aaroncarlson.projectmanagement.service.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private ValidationErrorService validationErrorService;

    @GetMapping("/{identifier}")
    public Iterable<Task> getProjectTasks(@PathVariable String identifier) {
        return taskService.findTaskByIdentifier(identifier);
    }

    @PostMapping("/{identifier}")
    public ResponseEntity<?> addTask(@Valid @RequestBody Task inboundTask,
                                             BindingResult result,
                                             @PathVariable String identifier) {

        ResponseEntity<?> error = validationErrorService.getErrorResponseEntity(result);
        if (error != null) {
            return error;
        }

        Task persistedTask = taskService.addTask(identifier, inboundTask);

        return new ResponseEntity<Task>(persistedTask, HttpStatus.CREATED);
    }

    @GetMapping("/{identifier}/{sequence}")
    public ResponseEntity<?> getProjectTaskBySequence(@PathVariable String identifier,
                                                      @PathVariable String sequence) {
        Task task = taskService.findTaskBySequence(identifier, sequence);

        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    @PatchMapping("/{identifier}/{sequence}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody Task updatedTask, BindingResult result,
                                               @PathVariable String identifier, @PathVariable String sequence) {

        ResponseEntity<?> error = validationErrorService.getErrorResponseEntity(result);
        if (error != null) {
            return error;
        }

        Task task = taskService.updateTaskBySequence(identifier, sequence, updatedTask);

        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{identifier}/{sequence}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String identifier,
                                               @PathVariable String sequence) {
        taskService.deleteTaskBySequence(identifier, sequence);

        return new ResponseEntity<String>("Task " + sequence + " was successfully deleted", HttpStatus.OK);
    }
}

package com.aaroncarlson.projectmanagement.service;

import com.aaroncarlson.projectmanagement.exception.CustomException;
import com.aaroncarlson.projectmanagement.model.Project;
import com.aaroncarlson.projectmanagement.model.Sprint;
import com.aaroncarlson.projectmanagement.model.Task;
import com.aaroncarlson.projectmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskService {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private SprintService sprintService;
    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(String identifier, Task task){
        /*
         * Overview: All Tasks must be associated to a single Project (project field cannot
         *  be null; therefore, a Task is an associated to Sprint through Project)
         */
        Project project = projectService.findByIdentifier(identifier);
        Integer nextProjectSequence = project.getSequence();
        Sprint sprint = sprintService.findByProject(project);

        task.setSprint(sprint);
        task.setSequence(project.getIdentifier() + "-" + nextProjectSequence);

        // Default Priority
        if (task.getPriority() == null) {
            task.setPriority(3);
        }

        // Default Status
        if (task.getStatus() == null) {
            task.setStatus("TODO");
        }

        // Only update Project Sequence if Task is added
        sprint.getProject().incrementSequence();

        return taskRepository.save(task);
    }

    public Iterable<Task> findTaskByIdentifier(String identifier) {
        Sprint sprint = sprintService.findProjectByIdentifier(identifier);

        return taskRepository.findBySprintOrderByPriority(sprint);
    }

    public Task findTaskBySequence(String identifier, String sequence) {
        Project project = projectService.findByIdentifier(identifier);
        Task task = findTaskBySequence(sequence);

        if (!task.getSprint().getProject().equals(project)) {
            throw  new CustomException("Task Sequence: " + sequence + ", does not exist for Identifier (" + identifier + ")");
        }

        return task;
    }

    public Task updateTaskBySequence(String identifier, String sequence, Task updatedTask) {
        // Checks to ensure a task is returned for sequence and identifier (otherwise an exception is thrown)
        Task task = findTaskBySequence(identifier, sequence);

        task = updatedTask;

        return taskRepository.save(task);
    }

    public void deleteTaskBySequence(String identifier, String sequence) {
        Task task = findTaskBySequence(identifier, sequence);

        taskRepository.delete(task);
    }

    private Task findTaskBySequence(String sequence) {
        Task task = taskRepository.findBySequence(sequence);

        if (task == null) {
            throw new CustomException("Sequence " + sequence + " does not exist.");
        }

        return task;
    }
}

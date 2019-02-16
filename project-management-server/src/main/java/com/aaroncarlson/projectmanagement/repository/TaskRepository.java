package com.aaroncarlson.projectmanagement.repository;

import com.aaroncarlson.projectmanagement.model.Sprint;
import com.aaroncarlson.projectmanagement.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findBySprintOrderByPriority(Sprint sprint);
    Task findBySequence(String sequence);

}

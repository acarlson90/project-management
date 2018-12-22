package com.aaroncarlson.projectmanagement.repository;

import com.aaroncarlson.projectmanagement.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}

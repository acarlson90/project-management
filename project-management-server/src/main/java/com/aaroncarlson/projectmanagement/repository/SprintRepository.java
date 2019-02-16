package com.aaroncarlson.projectmanagement.repository;

import com.aaroncarlson.projectmanagement.model.Project;
import com.aaroncarlson.projectmanagement.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

    Sprint findByProject(Project project);
}

package com.aaroncarlson.projectmanagement.repository;

import com.aaroncarlson.projectmanagement.model.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Long> {

    Backlog findByIdentifier(String identifier);
}

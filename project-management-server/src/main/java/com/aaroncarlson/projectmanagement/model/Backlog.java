package com.aaroncarlson.projectmanagement.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer sequence = 0;
    private String identifier;

    // OneToOne with project (each project has a backlog and a backlog only belongs to a single project)
    // OneToMany with Task (a backlog can have many tasks, but a task can only be long to a single project)

    // Constructor
    public Backlog() {

    }
}

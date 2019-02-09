package com.aaroncarlson.projectmanagement.model;

import com.aaroncarlson.projectmanagement.model.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Backlog extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer sequence = 0;
    private String identifier;

    /*
     * Definition - OneToOne relationship (child side) - Parent is Project
     *  Each Project has a single Backlog and a backlog only belongs to a single project)
     * @JsonIgnore - is the back part of reference - it will be omitted from serialization
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="project_id", nullable = false)
    @JsonBackReference
    private Project project;
    // OneToMany with Task (a backlog can have many tasks, but a task can only be long to a single project)

}

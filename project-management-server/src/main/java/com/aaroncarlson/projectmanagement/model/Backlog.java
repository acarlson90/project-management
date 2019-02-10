package com.aaroncarlson.projectmanagement.model;

import com.aaroncarlson.projectmanagement.model.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
     *  Each Project has a single Backlog and a Backlog only belongs to a single project
     * @JsonBackReference - is the back part of reference - it will be omitted from serialization
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="project_id", nullable = false)
    @JsonBackReference
    private Project project;
    /*
     * Definition - OneToMany relationship (parent side) - Parent is Backlog
     * Each Backlog has many Task(s) and a Task only belongs to a single Backlog
     */
    @OneToMany(fetch =  FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "backlog")
    @JsonManagedReference
    private List<Task> tasks = new ArrayList<>();

}

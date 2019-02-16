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
public class Sprint extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isActive = false;
    /*
     * Definition - OneToOne relationship (child side) - Parent is Project
     *  Each Project has a single Sprint and a Sprint only belongs to a single project
     * @JsonBackReference - is the back part of reference - it will be omitted from serialization
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
//    @JsonBackReference //Do NOT need if Project does NOT have @JsonManagedReference on Sprint field
    private Project project;
    /*
     * Definition - OneToMany relationship (parent side) - Parent is Sprint
     * Each Sprint has many Task(s) and a Task only belongs to a single Sprint
     * CascadeType.REFRESH should be on the ManagedReference side - this means that the associated relationships
     *  (in this case List<Task> will get refreshed in the database)
     * orphanRemoval - marks child entity (in this case Task) to be removed when they are no longer referenced
     *  from the "parent" entity (in this case Sprint)
     */
    @OneToMany(fetch =  FetchType.EAGER, cascade = CascadeType.REFRESH, mappedBy = "sprint", orphanRemoval = true)
    @JsonManagedReference
    private List<Task> tasks = new ArrayList<>();

}

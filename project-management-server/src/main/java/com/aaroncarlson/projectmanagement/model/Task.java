package com.aaroncarlson.projectmanagement.model;

import com.aaroncarlson.projectmanagement.model.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
public class Task extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false, unique = true)
    private String sequence;
    @NotBlank(message = "Project Summary is Required")
    private String summary;
    private String acceptanceCriteria;
    //TODO: Change to Enum
    private String status;
    //TODO: Change to Class (mapping numeric to Enum (ex: Critical, Blocker, etc.)
    private Integer priority;
    private Date dueDate;
    /*
     * Definition - ManyToOne relationship (child side) - Parent is Sprint
     *  Each Task has a single Sprint and many Task(s) belong to a single Sprint
     * CascadeType.REFRESH - Can delete/update a Task, then the Sprint is refreshed <-- remove this
     *  or the task won't get deleted
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sprint_id", updatable = false, nullable = false)
//    @JsonIgnore
    @JsonBackReference
    private Sprint sprint;

}

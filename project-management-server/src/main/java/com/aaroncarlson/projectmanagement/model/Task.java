package com.aaroncarlson.projectmanagement.model;

import com.aaroncarlson.projectmanagement.model.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(updatable = false)
    private String sequence;
    @NotBlank(message = "Project Summary is Required")
    private String summary;
    private String acceptanceCriteria;
    private String status;
    private Integer priority;
    private Date dueDate;
    // When persisting tasks we pass identifier in URL to know which backlog to persist to
    @Column(updatable = false)
    private String identifier;
    /*
     * Definition - ManyToOne relationship (child side) - Parent is Backlog
     *  Each Task has a single Backlog and many Task(s) belong to a single Backlog
     * CascadeType.REFRESH - Can delete/update a Task, then the Backlog is refreshed
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "backlog_id", updatable = false, nullable = false)
    @JsonBackReference
    private Backlog backlog;

}

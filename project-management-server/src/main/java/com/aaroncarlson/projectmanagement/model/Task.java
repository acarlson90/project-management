package com.aaroncarlson.projectmanagement.model;

import com.aaroncarlson.projectmanagement.model.audit.UserDateAudit;
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

    // ManyToOne with Backlog (a task can only belong to a singe backlog, and a backlog can have many tasks)

}

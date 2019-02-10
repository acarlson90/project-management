package com.aaroncarlson.projectmanagement.model;

import com.aaroncarlson.projectmanagement.model.audit.UserDateAudit;
import com.aaroncarlson.projectmanagement.util.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/*
 * @Data - Generates boilerplate @ToString, @EqualsAndHashCode, @Getter/@Setter & @RequiredArgsConstructor methods
 * @Entity - Defines that a class can be map to a repository table
 */
@Data
@Entity
public class Project extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Identifier is required")
    @Size(min = 4, max = 5, message = "Identifier must be between 4 - 5 characters")
    @Column(updatable = false, unique = true)
    private String identifier;
    @NotBlank(message = "Description is required")
    private String description;
    @JsonFormat(pattern = Constants.DATE_FORMAT_DAY_PERCISION)
    private Date startDate;
    @JsonFormat(pattern = Constants.DATE_FORMAT_DAY_PERCISION)
    private Date endDate;
    /*
     * Definition - OneToOne relationship (parent side) - Child is Backlog
     *  Each Project has a single Backlog and a backlog only belongs to a single project)
     * FetchType.EAGER - loads all the backlogs from the database when loading the Project (better to use LAZY)
     * Cascadetype.ALL - any change to Project must cascade to Backlog (ex: Persist, Merge, etc)
     * project - needs to be identical to the attribute name of the Project object in Backlog
     * @JsonManagedReference - is the forward part of reference - the one that gets serialized normally
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
    @JsonManagedReference
    private Backlog backlog;

}

package com.aaroncarlson.projectmanagement.model;

import com.aaroncarlson.projectmanagement.model.audit.UserDateAudit;
import lombok.Data;

import javax.persistence.*;
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
    private String name;
    private String identifier;
    private String description;
    private Date startDate;
    private Date endDate;

}

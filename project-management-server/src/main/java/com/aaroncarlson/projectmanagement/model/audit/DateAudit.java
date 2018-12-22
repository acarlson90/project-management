package com.aaroncarlson.projectmanagement.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

/*
 * @MappedSuperclass - ADesignates a class whose mapping information (fields) is applied to the entities that inherit
 * from it. A mapped superclass has no separate table defined for it.
 * AuditingEntityListener - Spring Data JPA provides a JPA entity listener class, AuditingEntityListener, which contains
 * the callback methods (annotated with @PrePersist and @PreUpdate annotations), which will be used to manage (persist or
 * update) audit related properties when entity is persisted or updated.
 * @EntityListeners - can be used to specify listener classes which will listen for the event of the entities through some
 * annotated methods. If any event is occurred, the relevant annotated method will get notified. Registered
 * AuditEntityListener as the entity listener class
 * @JsonIgnoreProperties - to skip/ignore JSON field when parsing the payload for the entity class
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public abstract class DateAudit implements Serializable {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

}

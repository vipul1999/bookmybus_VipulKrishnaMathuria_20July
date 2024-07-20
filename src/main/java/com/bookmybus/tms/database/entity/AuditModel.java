package com.bookmybus.tms.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt", "createdBy", "updatedBy", "requestId"},
        allowGetters = true,
        allowSetters = true
)
@Data
public abstract class AuditModel {
    @Column(
            name = "created_at",
            nullable = true,
            updatable = false
    )
    @CreatedDate
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "updated_at",
            nullable = false
    )
    @LastModifiedDate
    private Date updatedAt;
    @Column(
            name = "created_by",
            nullable = true,
            updatable = false
    )
    private String createdBy;
    @Column(
            name = "updated_by",
            nullable = true
    )
    private String updatedBy;
    @Column(
            name = "request_id",
            nullable = true
    )
    private String requestId;
}

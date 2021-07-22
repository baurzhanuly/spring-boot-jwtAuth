package com.example.springbootjwtAuth.models.audits;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt = new Date();


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at", nullable = false)
    private Date deletedAt = new Date();


    @PrePersist
    public void prePersist(){
        if(this.createdAt == null){
            this.createdAt = new Date();
        }
        this.updatedAt = new Date();
    }

    @PreUpdate
    private void preUpdate(){
        this.updatedAt = new Date();
    }
}

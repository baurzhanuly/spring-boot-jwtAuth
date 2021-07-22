package com.example.springbootjwtAuth.models.entities;

import com.example.springbootjwtAuth.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AuditModel {

    @Column(name = "name", unique = true)
    private String name;

}

package com.hnbafrica.userMgmt.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity{
    @Column(name = "role")
    private String role;
}

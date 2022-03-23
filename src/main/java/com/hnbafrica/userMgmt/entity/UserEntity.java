package com.hnbafrica.userMgmt.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@JsonIgnoreProperties(value={ "password" },allowSetters= true)
public class UserEntity extends BaseEntity{

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private String password;

    @Column(name="email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    private boolean enable;

    @Column(updatable = false)
    private String verificationCode;

    public UserEntity(String firstName, String lastName, String password, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}

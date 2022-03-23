package com.hnbafrica.userMgmt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;

@Data
@JsonIgnoreProperties(value={ "password" },allowSetters= true)
public class User {

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String phone;

}

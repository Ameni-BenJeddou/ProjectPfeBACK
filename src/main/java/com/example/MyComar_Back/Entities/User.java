package com.example.MyComar_Back.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data

@Table(name = "User")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id_user;
    private String firstName;
    private String lastName;
    private Date Date_birth;
    private String Region;
    private String Phone_number;
    private Boolean Email_notification_active;
    private Boolean Work_status; //0=not employed 1=employed
    private Boolean Gender; //0=male 1=female

    @OneToOne
    private Account Id_account;
    @JsonIgnore
    @OneToMany(mappedBy = "Id_User")
    private List<Education_details> Education_details;
    @JsonIgnore
    @OneToMany(mappedBy = "Id_User")
    private List<Experience_details> Experience_details;
    @JsonIgnore
    @OneToMany(mappedBy = "Id_User")
    private List<Job_Application> Job_Application;
    @JsonIgnore
    @OneToMany(mappedBy = "Id_User")
    private List<User_Skill_Set> Id_User_set;
    @JsonIgnore
    @OneToMany(mappedBy = "Id_User")
    private List<Spontaneous_application> Spontaneous_Application;
}
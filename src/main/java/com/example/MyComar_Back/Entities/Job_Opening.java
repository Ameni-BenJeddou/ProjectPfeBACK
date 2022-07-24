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

public class Job_Opening implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id_Opening;
    private String Job_type;
    private String Description;
    private String Department;
    private String Location;
    private Date Creation_Date;
    private Date Closure_Date;
    private Boolean Is_Active; // 0=inactive , 1=active
    private Boolean Gender; // 0=male,1=female
    private Integer Relevant_Education_Level;
    private Integer Relevant_Experience_Level;

    @JsonIgnore
    @OneToMany(mappedBy = "Id_Opening")
    private List<Job_Application> Id_Application;
    @JsonIgnore
    @OneToMany(mappedBy = "Id_Opening")
    private List<Job_Skill_Set> Id_Job_Set;


}

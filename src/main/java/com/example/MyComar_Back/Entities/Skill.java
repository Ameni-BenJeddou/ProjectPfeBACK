package com.example.MyComar_Back.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data

public class Skill implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id_Skill;
    private String Skill_Name;
    @JsonIgnore
    @OneToMany(mappedBy = "Id_Skill")
    private List<User_Skill_Set> Id_User_Set;
    @JsonIgnore
    @OneToMany(mappedBy = "Id_Skill")
    private List<Job_Skill_Set> Id_Job_Set;

}

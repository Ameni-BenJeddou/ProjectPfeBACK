package com.example.MyComar_Back.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data

@Table(name = "Job_Skill_Set")
public class Job_Skill_Set implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id_Job_Set;
    private Integer Level;
    @OneToOne
    private Job_Opening Id_Opening;
    @OneToOne
    private Skill Id_Skill;

}

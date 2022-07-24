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

@Table(name = "User_Skill_Set")

public class User_Skill_Set implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id_User_Set;
    private Integer Level;

    @OneToOne
    private User Id_User;
    @OneToOne
    private Skill Id_Skill;


}

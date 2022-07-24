package com.example.MyComar_Back.Entities;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data

@Table(name = "Spontaneous_application")
public class Spontaneous_application implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id_S_Application;
    private Date Creation_Date;
    private String Job_Type;
    private Integer Relevant_Education_Level;
    private Integer Relevant_Experience_Level;
    @OneToOne
    private User Id_User;

}

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

@Table(name = "Job_Application")
public class Job_Application implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id_Application;
    private Date Creation_Date;
    private Boolean Gender;
    private Integer Relevant_Education_Level;
    private Integer Relevant_Experience_Level;
    private String Status;

    @OneToOne
    private Job_Opening Id_Opening;

    @OneToOne
    private User Id_User;

}

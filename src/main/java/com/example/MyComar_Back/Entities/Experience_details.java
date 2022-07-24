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

@Table(name = "Experience_details")
public class Experience_details implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id_Experience;
    private String Company_name;
    private String Post;
    private String Description;
    private Date Start_date;
    private Date End_date;
    private Boolean is_current; //0=not current , 1=current

    @OneToOne
    private User Id_User;
}

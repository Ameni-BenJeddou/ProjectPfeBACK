package com.example.MyComar_Back.reposotoryInterface;


import com.example.MyComar_Back.Entities.Education_details;

import java.util.List;
import java.util.Optional;

public interface Education_details_Repo_Interface {

    void saveEducation_details( Education_details education_details);
    void updateEducation_details( Education_details education_details);
    List<Education_details> ListEducation_details();
    void removeEducation_details(Long Id);
    Optional<Education_details> Find_Education_details(Long Id);
}

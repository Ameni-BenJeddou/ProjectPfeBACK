package com.example.MyComar_Back.reposotoryInterface;


import com.example.MyComar_Back.Entities.Experience_details;

import java.util.List;
import java.util.Optional;

public interface Experience_details_Repo_Interface {

    void saveExperience_details( Experience_details experience_details);
    void updateExperience_details( Experience_details experience_details);
    List<Experience_details> ListExperience_details();
    void removeExperience_details(Long Id);
    Optional<Experience_details> Find_Experience_details(Long Id);
}

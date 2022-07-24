package com.example.MyComar_Back.ResposotoryImplementaion;

import com.example.MyComar_Back.Entities.Experience_details;
import com.example.MyComar_Back.reposotory.Experience_details_repository;
import com.example.MyComar_Back.reposotoryInterface.Experience_details_Repo_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class Experience_details_repo_implementation implements Experience_details_Repo_Interface {
    @Autowired

    Experience_details_repository skill_repository;
    @Override
    public List<Experience_details> ListExperience_details(){
        return skill_repository.findAll();
    }

    @Override
    public void saveExperience_details (Experience_details experience_details) {
        skill_repository.save(experience_details);
    }

    @Override
    public void removeExperience_details(Long id) {
        skill_repository.deleteById(id);
    }
    @Override
    public void updateExperience_details (Experience_details experience_details)
    {
        skill_repository.save(experience_details);
    }
    @Override
    public Optional<Experience_details> Find_Experience_details(Long Id) {
        return skill_repository.findById(Id);
    }
}

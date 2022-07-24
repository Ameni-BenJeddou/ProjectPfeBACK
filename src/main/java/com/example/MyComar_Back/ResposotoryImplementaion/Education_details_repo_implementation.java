package com.example.MyComar_Back.ResposotoryImplementaion;

import com.example.MyComar_Back.Entities.Education_details;
import com.example.MyComar_Back.reposotory.Education_details_repository;
import com.example.MyComar_Back.reposotoryInterface.Education_details_Repo_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class Education_details_repo_implementation implements Education_details_Repo_Interface {
    @Autowired

    Education_details_repository education_details_repository;
    @Override
    public List<Education_details> ListEducation_details(){
        return education_details_repository.findAll();
    }

    @Override
    public void saveEducation_details (Education_details education_details) {
        education_details_repository.save(education_details);
    }

    @Override
    public void removeEducation_details(Long id) {
        education_details_repository.deleteById(id);
    }
    @Override
    public void updateEducation_details (Education_details education_details)
    {
        education_details_repository.save(education_details);
    }
    @Override
    public Optional<Education_details> Find_Education_details(Long Id) {
        return education_details_repository.findById(Id);
    }
}

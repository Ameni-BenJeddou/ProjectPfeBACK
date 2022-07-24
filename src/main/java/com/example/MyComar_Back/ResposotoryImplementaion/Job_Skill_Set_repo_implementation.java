package com.example.MyComar_Back.ResposotoryImplementaion;

import com.example.MyComar_Back.Entities.Job_Skill_Set;
import com.example.MyComar_Back.reposotory.Job_Skill_Set_repository;
import com.example.MyComar_Back.reposotoryInterface.Job_Skill_Set_Repo_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class Job_Skill_Set_repo_implementation implements Job_Skill_Set_Repo_Interface {
    @Autowired

    Job_Skill_Set_repository job_skill_set_repository;
    @Override
    public List<Job_Skill_Set> ListJob_Skill_Set(){
        return job_skill_set_repository.findAll();
    }

    @Override
    public void saveJob_Skill_Set (Job_Skill_Set job_skill_set) {
        job_skill_set_repository.save(job_skill_set);
    }
    @Override
    public void removeJob_Skill_Set(Long id) {
        job_skill_set_repository.deleteById(id);
    }
    @Override
    public void updateJob_Skill_Set (Job_Skill_Set job_skill_set)
    {
        job_skill_set_repository.save(job_skill_set);
    }
    @Override
    public Optional<Job_Skill_Set> Find_Job_Skill_Set(Long Id) {
        return job_skill_set_repository.findById(Id);
    }
}

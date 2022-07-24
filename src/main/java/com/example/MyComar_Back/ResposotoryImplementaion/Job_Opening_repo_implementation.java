package com.example.MyComar_Back.ResposotoryImplementaion;

import com.example.MyComar_Back.Entities.Job_Opening;
import com.example.MyComar_Back.reposotory.Job_Opening_repository;
import com.example.MyComar_Back.reposotoryInterface.Job_Opening_Repo_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class Job_Opening_repo_implementation implements Job_Opening_Repo_Interface {
    @Autowired

    Job_Opening_repository job_opening_repository;
    @Override
    public List<Job_Opening> ListJob_Opening(){
        return job_opening_repository.findAll();
    }



    @Override
    public void saveJob_Opening (Job_Opening job_opening) {
        job_opening_repository.save(job_opening);
    }

    @Override
    public void removeJob_Opening(Long id) {
        job_opening_repository.deleteById(id);
    }
    @Override
    public void updateJob_Opening (Job_Opening job_opening)
    {
        job_opening_repository.save(job_opening);
    }
    @Override
    public Optional<Job_Opening> Find_Job_Opening(Long Id) {
        return job_opening_repository.findById(Id);
    }
}

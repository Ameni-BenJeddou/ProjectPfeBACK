package com.example.MyComar_Back.ResposotoryImplementaion;

import com.example.MyComar_Back.Entities.Job_Application;
import com.example.MyComar_Back.reposotory.Job_Application_repository;
import com.example.MyComar_Back.reposotoryInterface.Job_Application_Repo_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class Job_Application_repo_implementation implements Job_Application_Repo_Interface {
    @Autowired

    Job_Application_repository job_application_repository;
    @Override
    public List<Job_Application> ListJob_Application(){
        return job_application_repository.findAll();
    }
    @Override
    public void saveJob_Application (Job_Application job_application) {
        job_application_repository.save(job_application);
    }
    @Override
    public void removeJob_Application (Long id) {
        job_application_repository.deleteById(id);
    }
    @Override
    public void updateJob_Application (Job_Application job_application)
    {
        job_application_repository.save(job_application);
    }
    @Override
    public Optional<Job_Application> Find_Job_Application(Long Id) {
        return job_application_repository.findById(Id);
    }
}

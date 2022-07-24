package com.example.MyComar_Back.reposotoryInterface;

import com.example.MyComar_Back.Entities.Job_Application;

import java.util.List;
import java.util.Optional;

public interface Job_Application_Repo_Interface {

    void saveJob_Application( Job_Application job_application);
    void updateJob_Application( Job_Application job_application);
    List<Job_Application> ListJob_Application();
    void removeJob_Application(Long Id);
    Optional<Job_Application> Find_Job_Application(Long Id);
}

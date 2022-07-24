package com.example.MyComar_Back.reposotoryInterface;

import com.example.MyComar_Back.Entities.Job_Opening;

import java.util.List;
import java.util.Optional;

public interface Job_Opening_Repo_Interface {

    void saveJob_Opening( Job_Opening U);
    void updateJob_Opening( Job_Opening c);
    List<Job_Opening> ListJob_Opening();
    void removeJob_Opening(Long Id);
    Optional<Job_Opening> Find_Job_Opening(Long Id);
}

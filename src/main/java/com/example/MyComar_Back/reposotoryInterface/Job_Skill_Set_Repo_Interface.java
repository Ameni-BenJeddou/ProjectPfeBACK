package com.example.MyComar_Back.reposotoryInterface;

import com.example.MyComar_Back.Entities.Job_Skill_Set;

import java.util.List;
import java.util.Optional;

public interface Job_Skill_Set_Repo_Interface {

    void saveJob_Skill_Set( Job_Skill_Set job_skill_set);
    void updateJob_Skill_Set( Job_Skill_Set job_skill_set);
    List<Job_Skill_Set> ListJob_Skill_Set();
    void removeJob_Skill_Set(Long Id);
    Optional<Job_Skill_Set> Find_Job_Skill_Set(Long Id);
}

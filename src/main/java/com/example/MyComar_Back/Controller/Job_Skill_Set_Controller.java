package com.example.MyComar_Back.Controller;

import com.example.MyComar_Back.Entities.Job_Application;
import com.example.MyComar_Back.Entities.Job_Skill_Set;
import com.example.MyComar_Back.ResposotoryImplementaion.Job_Skill_Set_repo_implementation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping(value="/User")
public class Job_Skill_Set_Controller {
    @Autowired
    private Job_Skill_Set_repo_implementation job_skill_set_repo_implementation;

    @GetMapping(value="/Find_Job_Skill_Set/{id}")
    public Optional<Job_Skill_Set> Find_Job_Skill_Set (@PathVariable(value="id") String id){
        return job_skill_set_repo_implementation.Find_Job_Skill_Set(Long.parseLong(id));
    }

    @PostMapping("/Add_Job_Skill_Set")
    public Job_Skill_Set Create_Skill (@Valid @RequestBody Job_Skill_Set job_skill_set)
    {
        job_skill_set_repo_implementation.saveJob_Skill_Set(job_skill_set);
        return job_skill_set;
    }

    @PutMapping("/Update_Job_Skill_Set")
    public String Update_Job_Skill_Set(@PathVariable @RequestBody Job_Skill_Set job_skill_set)
    {
        job_skill_set_repo_implementation.updateJob_Skill_Set(job_skill_set);
        return"success:Update Done!";
    }
    @GetMapping("/Getspecificjob_skill_set/{id}")
    public List<Job_Skill_Set> Get_specific_skill_set(@PathVariable Long id){
        List<Job_Skill_Set> specific_skill_set = new ArrayList<Job_Skill_Set>();
        List<Job_Skill_Set> allsets= job_skill_set_repo_implementation.ListJob_Skill_Set();
        for (Job_Skill_Set set : allsets) {
            if (set.getId_Opening().getId_Opening().equals(id))
                specific_skill_set.add(set);
        }
        return specific_skill_set;
    }
    @GetMapping("/GetALL_Job_Skill_Set")
    public List<Job_Skill_Set> Get_All_Job_Skill_Set(){
        return job_skill_set_repo_implementation.ListJob_Skill_Set();
    }

    @DeleteMapping(value="/Delete_Job_Skill_Set/{id}")
    public String Delete_Job_Skill_Set(@PathVariable String id)
    {
        job_skill_set_repo_implementation.removeJob_Skill_Set(Long.parseLong(id));
        return "Job Skill Set Deleted!";
    }



}
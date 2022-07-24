package com.example.MyComar_Back.Controller;

import com.example.MyComar_Back.Entities.Job_Opening;
import com.example.MyComar_Back.reposotoryInterface.Job_Opening_Repo_Interface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value="/User")
public class Job_Opening_Controller {
    @Autowired
    private Job_Opening_Repo_Interface job_opening_repo_interface;

    @GetMapping(value = "/Find_Job_Opening/{id}")
    public Optional<Job_Opening> Find_Job_Opening(@PathVariable(value = "id") String id) {
        return job_opening_repo_interface.Find_Job_Opening(Long.parseLong(id));
    }

    @PostMapping(value = "/Admin/Add_Job_Opening")
    public Job_Opening Create_Job_Opening(@Valid @RequestBody Job_Opening job_opening) {
        job_opening_repo_interface.saveJob_Opening(job_opening);
        return job_opening;
    }

    @PutMapping(value = "/Admin/Update_Job_Opening/{id}")
    public ResponseEntity<Job_Opening> Update_Job_Opening(@PathVariable("id") long id, @RequestBody Job_Opening job_opening) {
        Optional<Job_Opening> job_openingData = job_opening_repo_interface.Find_Job_Opening(id);
        if (job_openingData.isPresent()) {
            Job_Opening updatedvacancy = job_openingData.get();
            updatedvacancy.setDescription(job_opening.getDescription());
            updatedvacancy.setGender(job_opening.getGender());
            updatedvacancy.setDepartment(job_opening.getDepartment());
            updatedvacancy.setJob_type(job_opening.getJob_type());
            updatedvacancy.setLocation(job_opening.getLocation());
            updatedvacancy.setRelevant_Education_Level(job_opening.getRelevant_Education_Level());
            updatedvacancy.setRelevant_Experience_Level(job_opening.getRelevant_Experience_Level());
            updatedvacancy.setCreation_Date(job_opening.getCreation_Date());
            updatedvacancy.setClosure_Date(job_opening.getClosure_Date());
            updatedvacancy.setIs_Active(job_opening.getIs_Active());
            job_opening_repo_interface.updateJob_Opening(updatedvacancy);
            return new ResponseEntity<>(updatedvacancy, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @GetMapping(value = "/GetALL_Job_Opening")
    public List<Job_Opening> Get_All_Job_Opening() {
        return job_opening_repo_interface.ListJob_Opening();
    }

    @GetMapping(value = "/GetALLactive_Job_Opening")
    public List<Job_Opening> Get_Allactive_Job_Opening() {

        List<Job_Opening> active = new ArrayList<Job_Opening>();
        List<Job_Opening> allvacany = job_opening_repo_interface.ListJob_Opening();
        for (Job_Opening job : allvacany) {
            if (job.getIs_Active() != false)
                active.add(job);
        }
        return active;
    }


}
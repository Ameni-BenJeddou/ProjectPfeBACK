package com.example.MyComar_Back.Controller;

import com.example.MyComar_Back.Entities.Job_Application;
import com.example.MyComar_Back.Entities.Job_Opening;
import com.example.MyComar_Back.reposotoryInterface.Job_Application_Repo_Interface;
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
public class Job_Application_Controller {
    @Autowired
    private Job_Application_Repo_Interface job_application_repo_interface;

    @GetMapping(value="/Find_Job_Application/{id}")
    public Optional<Job_Application> Find_Application (@PathVariable(value="id") String id){
        return job_application_repo_interface.Find_Job_Application(Long.parseLong(id));
    }

    @PostMapping("/Add_Job_Application")
    public Job_Application Create_Job_Application (@Valid @RequestBody Job_Application job_application)
    {
        job_application_repo_interface.saveJob_Application(job_application);
        return job_application;
    }

    @PutMapping("/Update_Job_Application/{id}")
    public ResponseEntity<Job_Application> Update_Job_Application(@PathVariable("id") long id, @RequestBody Job_Application job_application)
    { Optional<Job_Application> job_applicationData = job_application_repo_interface.Find_Job_Application(id);
        if (job_applicationData.isPresent()) {
            Job_Application updatedapp = job_applicationData.get();
            updatedapp.setStatus(job_application.getStatus());
            updatedapp.setGender(job_application.getGender());
            updatedapp.setRelevant_Education_Level(job_application.getRelevant_Education_Level());
            updatedapp.setRelevant_Experience_Level(job_application.getRelevant_Experience_Level());
            updatedapp.setCreation_Date(job_application.getCreation_Date());
            job_application_repo_interface.updateJob_Application(updatedapp);
            return new ResponseEntity<>(updatedapp, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/GetALL_Job_Application")
    public List<Job_Application> Get_All_Job_Application(){
        return job_application_repo_interface.ListJob_Application();
    }
    @GetMapping("/Getuser_Job_Application/{id}")
    public List<Job_Application> Get_user_Job_Application(@PathVariable Long id){
        List<Job_Application> userapps = new ArrayList<Job_Application>();
        List<Job_Application> allapp= job_application_repo_interface.ListJob_Application();
        for (Job_Application app : allapp) {
            if (app.getId_User().getId_user().equals(id))
                userapps.add(app);
        }
        return userapps;
    }

    @GetMapping("/Getshortlist_Job_Application")
    public List<Job_Application> Get_shortlist_Job_Application(){
        List<Job_Application> shortlist = new ArrayList<Job_Application>();
        List<Job_Application> allapp= job_application_repo_interface.ListJob_Application();
        for (Job_Application app : allapp) {
            if (app.getStatus().equals("Shortlist"))
                shortlist.add(app);
        }
        return shortlist;
    }
    @GetMapping("/Getactive_Job_Application")
    public List<Job_Application> Get_active_Job_Application(){
        List<Job_Application> active = new ArrayList<Job_Application>();
        List<Job_Application> allapp= job_application_repo_interface.ListJob_Application();
        for (Job_Application app : allapp) {
            if (!(app.getStatus() .equals("Declined"))&&(!(app.getStatus() .equals("Hired"))))
                active.add(app);
        }
        return active;
    }

    @GetMapping("/Getactivevacancy_Job_Application/{id}")
    public List<Job_Application> Get_activevacany_Job_Application(@PathVariable Long id){
        List<Job_Application> active = new ArrayList<Job_Application>();
        List<Job_Application> allapp= job_application_repo_interface.ListJob_Application();
        for (Job_Application app : allapp) {
            if (!(app.getStatus() .equals("Declined"))&&(!(app.getStatus().equals("Hired")))&&(app.getId_Opening().getId_Opening().equals(id)))
                active.add(app);
        }
        return active;
    }
    @GetMapping("/Getshortlistvacancy_Job_Application/{id}")
    public List<Job_Application> Get_shortlistvacancy_Job_Application(@PathVariable Long id){
        List<Job_Application> shortlist = new ArrayList<Job_Application>();
        List<Job_Application> allapp= job_application_repo_interface.ListJob_Application();
        for (Job_Application app : allapp) {
            if ((app.getStatus().equals("Shortlist"))&&(app.getId_Opening().getId_Opening() .equals(id)))
                shortlist.add(app);
        }
        return shortlist;
    }

    @DeleteMapping(value="/Delete_Job_Application/{id}")
    public String Delete_Job_Application(@PathVariable String id)
    {
        job_application_repo_interface.removeJob_Application(Long.parseLong(id));
        return "Job Application Deleted!";
    }

}
package com.example.MyComar_Back.Controller;

import com.example.MyComar_Back.Entities.Education_details;
import com.example.MyComar_Back.Entities.Experience_details;
import com.example.MyComar_Back.Entities.User;
import com.example.MyComar_Back.reposotoryInterface.Experience_details_Repo_Interface;
import com.example.MyComar_Back.reposotoryInterface.User_Repo_Interface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/User")
public class Experience_details_Controller {
    @Autowired
    private Experience_details_Repo_Interface experience_details_repo_interface;

    @GetMapping(value="/Find_Experience_details/{id}")

    public ResponseEntity<Experience_details> Find_Experience_details(@PathVariable("id") long id) {
        Optional<Experience_details> experience_details = experience_details_repo_interface.Find_Experience_details(id);
        if (experience_details.isPresent()) {
            return new ResponseEntity<>(experience_details.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Add_Experience_details")

    public Experience_details Create_Experience_details(@RequestBody Experience_details experience_details) {

            experience_details_repo_interface.saveExperience_details(experience_details);
            return experience_details;
    }

    @PutMapping("/Update_Experience_details/{id}")

    public ResponseEntity<Experience_details> Update_Experience_details(@PathVariable("id") long id, @RequestBody Experience_details experience_details) {
        Optional<Experience_details> experience_detailsData = experience_details_repo_interface.Find_Experience_details(id);
        if (experience_detailsData.isPresent()) {
            Experience_details updatedexperience_details = experience_detailsData.get();
            updatedexperience_details.setCompany_name(experience_details.getCompany_name());
            updatedexperience_details.setPost(experience_details.getPost());
            updatedexperience_details.setDescription(experience_details.getDescription());
            updatedexperience_details.setStart_date(experience_details.getStart_date());
            updatedexperience_details.setEnd_date(experience_details.getEnd_date());
            updatedexperience_details.setIs_current(experience_details.getIs_current());
            experience_details_repo_interface.updateExperience_details(updatedexperience_details);
            return new ResponseEntity<>(updatedexperience_details, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/GetALL_Experience_details")
    public ResponseEntity<List<Experience_details>> Get_All_Experience_details() {
        try {
            List<Experience_details> experience_details = new ArrayList<Experience_details>();
            experience_details_repo_interface.ListExperience_details().forEach(experience_details::add);

            return new ResponseEntity<>(experience_details, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/GetspecificExperience_details/{id}")
    public List<Experience_details> Get_specific_Experience_details(@PathVariable Long id){
        List<Experience_details> specific_experience = new ArrayList<Experience_details>();
        List<Experience_details> all= experience_details_repo_interface.ListExperience_details();
        for (Experience_details exp : all) {
            if (exp.getId_User().getId_user().equals(id))
                specific_experience.add(exp);
        }
        return specific_experience;
    }

    @DeleteMapping(value="/Delete_Experience_details/{id}")
    public ResponseEntity<HttpStatus> Delete_Education_details(@PathVariable("id") long id) {
        try {
            experience_details_repo_interface.removeExperience_details(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
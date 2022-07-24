package com.example.MyComar_Back.Controller;

import com.example.MyComar_Back.Entities.Education_details;
import com.example.MyComar_Back.reposotoryInterface.Education_details_Repo_Interface;
import com.example.MyComar_Back.reposotoryInterface.User_Repo_Interface;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/User")
public class Education_details_Controller {
    @Autowired
    private Education_details_Repo_Interface education_details_repo_interface;
    private User_Repo_Interface User_Repo;


    @GetMapping(value="/Find_Education_details/{id}")

    public ResponseEntity<Education_details> Find_Education_details(@PathVariable("id") long id) {
        Optional<Education_details> education_details = education_details_repo_interface.Find_Education_details(id);
        if (education_details.isPresent()) {
            return new ResponseEntity<>(education_details.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Add_Education_details")

    public Education_details Create_Education_details(@RequestBody Education_details education_details) {

            education_details_repo_interface.saveEducation_details(education_details);

            return education_details;
    }


    @PutMapping("/Update_Education_details/{id}")

    public ResponseEntity<Education_details> Update_Education_details(@PathVariable("id") long id, @RequestBody Education_details education_details) {
        Optional<Education_details> education_detailsData = education_details_repo_interface.Find_Education_details(id);
        if (education_detailsData.isPresent()) {
            Education_details updatededucation_details = education_detailsData.get();
            updatededucation_details.setDegree_name(education_details.getDegree_name());
            updatededucation_details.setInstitute(education_details.getInstitute());
            updatededucation_details.setMajor(education_details.getMajor());
            updatededucation_details.setStart_date(education_details.getStart_date());
            updatededucation_details.setEnd_date(education_details.getEnd_date());
            updatededucation_details.setIs_current(education_details.getIs_current());
            education_details_repo_interface.updateEducation_details(updatededucation_details);
            return new ResponseEntity<>(updatededucation_details, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/GetALL_Education_details")

    public ResponseEntity<List<Education_details>> Get_All_Education_details() {
        try {
            List<Education_details> education_details = new ArrayList<Education_details>();
            education_details_repo_interface.ListEducation_details().forEach(education_details::add);

            return new ResponseEntity<>(education_details, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/GetspecificEducation_details/{id}")
    public List<Education_details> Get_specific_skill_set(@PathVariable Long id){
        List<Education_details> specific_education = new ArrayList<Education_details>();
        List<Education_details> all= education_details_repo_interface.ListEducation_details();
        for (Education_details edu : all) {
            if (edu.getId_User().getId_user().equals(id))
                specific_education.add(edu);
        }
        return specific_education;
    }

    @DeleteMapping(value="/Delete_Education_details/{id}")

    public ResponseEntity<HttpStatus> Delete_Education_details(@PathVariable("id") long id) {
        try {
            education_details_repo_interface.removeEducation_details(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
package com.example.MyComar_Back.Controller;

import com.example.MyComar_Back.Entities.Experience_details;
import com.example.MyComar_Back.Entities.Skill;
import com.example.MyComar_Back.Entities.User;
import com.example.MyComar_Back.reposotoryInterface.Skill_Repo_Interface;
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
public class Skill_Controller {
    @Autowired
    private Skill_Repo_Interface Skill_Repo;

    @GetMapping(value="/Find_Skill/{id}")

    public ResponseEntity<Skill> FindSkill(@PathVariable("id") long id) {
        Optional<Skill> skill = Skill_Repo.Find_Skill(id);
        if (skill.isPresent()) {
            return new ResponseEntity<>(skill.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Add_Skill")

    public ResponseEntity<Skill> Create_Skill(@RequestBody String name) {
        try {
            Skill added_skill = new Skill();
            added_skill.setSkill_Name(name);
            Skill_Repo.saveSkill(added_skill);
            return new ResponseEntity<>(added_skill, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Update_Skill/{id}")

    public ResponseEntity<Skill> Update_Skill(@PathVariable("id") long id, @RequestBody Skill skill) {
        Optional<Skill> skillData = Skill_Repo.Find_Skill(id);
        if (skillData.isPresent()) {
            Skill updatedskill = skillData.get();
            updatedskill.setSkill_Name(skill.getSkill_Name());
            Skill_Repo.updateSkill(updatedskill);
            return new ResponseEntity<>(updatedskill, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/GetALL_Skills")

    public ResponseEntity<List<Skill>> Get_All_Skills() {
        try {
            List<Skill> skills = new ArrayList<Skill>();
            Skill_Repo.ListSkill().forEach(skills::add);

            return new ResponseEntity<>(skills, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value="/Delete_Skill/{id}")

    public ResponseEntity<HttpStatus> Delete_Skill(@PathVariable("id") long id) {
        try {
            Skill_Repo.removeSkill(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
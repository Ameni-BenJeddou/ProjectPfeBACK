package com.example.MyComar_Back.Controller;

import com.example.MyComar_Back.Entities.*;
import com.example.MyComar_Back.reposotoryInterface.User_Repo_Interface;
import com.example.MyComar_Back.reposotoryInterface.User_Skill_Set_Repo_Interface;
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
public class User_Skill_Set_Controller {
    @Autowired
    private User_Skill_Set_Repo_Interface User_Skill_Set_Repo;

    @GetMapping(value="/User_Skill_Set/{id}")

    public ResponseEntity<User_Skill_Set> Find_User_Skill_Set(@PathVariable("id") long id) {
        Optional<User_Skill_Set> user_skill_set = User_Skill_Set_Repo.Find_User_Skill_Set(id);
        if (user_skill_set.isPresent()) {
            return new ResponseEntity<>(user_skill_set.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Add_User_Skill_Set")

    public User_Skill_Set Create_User_Skill_Set(@RequestBody User_Skill_Set user_skill_set) {

        User_Skill_Set_Repo.saveUser_Skill_Set(user_skill_set);
        return user_skill_set;
    }
    @GetMapping("/Getspecificuser_skill_set/{id}")
    public List<User_Skill_Set> Get_specific_skill_set(@PathVariable Long id){
        List<User_Skill_Set> specific_skill_set = new ArrayList<User_Skill_Set>();
        List<User_Skill_Set> allsets= User_Skill_Set_Repo.ListUser_Skill_Set();
        for (User_Skill_Set set : allsets) {
            if (set.getId_User().getId_user().equals(id))
                specific_skill_set.add(set);
        }
        return specific_skill_set;
    }
    @PutMapping("/Update_User_Skill_Set/{id}")

    public ResponseEntity<User_Skill_Set> Update_Skill_set(@PathVariable("id") long id, @RequestBody User_Skill_Set user_skill_set) {
        Optional<User_Skill_Set> user_skill_setData = User_Skill_Set_Repo.Find_User_Skill_Set(id);
        if (user_skill_setData.isPresent()) {
            User_Skill_Set updated_user_skill_set = user_skill_setData.get();
            updated_user_skill_set.setLevel(user_skill_set.getLevel());
            User_Skill_Set_Repo.updateUser_Skill_Set(updated_user_skill_set);
            return new ResponseEntity<>(updated_user_skill_set, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/GetALL_User_Skill_Set")

    public ResponseEntity<List<User_Skill_Set>> Get_All_Users_Skill_Set() {
        try {
            List<User_Skill_Set> user_skill_sets = new ArrayList<User_Skill_Set>();
            User_Skill_Set_Repo.ListUser_Skill_Set().forEach(user_skill_sets::add);

            return new ResponseEntity<>(user_skill_sets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value="/Delete_User_Skill_Set/{id}")

    public ResponseEntity<HttpStatus> Delete_Education_details(@PathVariable("id") long id) {
        try {
            User_Skill_Set_Repo.removeUser_Skill_Set(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
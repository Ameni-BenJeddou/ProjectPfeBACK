package com.example.MyComar_Back.Controller;

import com.example.MyComar_Back.Entities.Account;
import com.example.MyComar_Back.Entities.User;
import com.example.MyComar_Back.reposotoryInterface.AccountreposotoryInterface;
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
public class User_Controller {
    @Autowired
    private User_Repo_Interface User_Repo;

    @GetMapping(value="/Find_User/{id}")
    public ResponseEntity<User> Find_User(@PathVariable("id") long id) {
        Optional<User> user = User_Repo.FindByIdaccount(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/Findspecific_User/{id}")
    public ResponseEntity<User> Findspecific_User(@PathVariable("id") long id) {
        Optional<User> user = User_Repo.FindById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Add_User")
    public User Create_User(@Valid @RequestBody User user) {
                User_Repo.saveUser(user);

            return user;

    }
    @PutMapping("/Update_Userbasicinfo/{id}")
    public ResponseEntity<User> Update_Userbasicinfo(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userData = User_Repo.FindById(id);
        if (userData.isPresent()) {
            User updateduser = userData.get();
            updateduser.setFirstName(user.getFirstName());
            updateduser.setLastName(user.getLastName());
            updateduser.setDate_birth(user.getDate_birth());
            updateduser.setGender(user.getGender());
            updateduser.setWork_status(user.getWork_status());
            updateduser.setEmail_notification_active(user.getEmail_notification_active());
            updateduser.setPhone_number(user.getPhone_number());
            updateduser.setRegion(user.getRegion());
            User_Repo.updateUser(updateduser);
            return new ResponseEntity<>(updateduser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/GetALL_User")
    // if have a filtering field add a paramater as shows : @RequestParam(required = false) String paramater
    public ResponseEntity<List<User>> Get_users() {
        try {
            List<User> Users = new ArrayList<User>();
                User_Repo.ListUser().forEach(Users::add);

            return new ResponseEntity<>(Users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value="/Delete_User/{id}")
    public ResponseEntity<HttpStatus> Delete_User(@PathVariable("id") long id) {
        try {
            User_Repo.removeUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
package com.example.MyComar_Back.ResposotoryImplementaion;

import com.example.MyComar_Back.Entities.User_Skill_Set;
import com.example.MyComar_Back.reposotory.User_Skill_Set_repository;
import com.example.MyComar_Back.reposotoryInterface.User_Skill_Set_Repo_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class User_Skill_Set_repo_implementation implements User_Skill_Set_Repo_Interface {
    @Autowired

    User_Skill_Set_repository user_skill_set_repository;
    @Override
    public List<User_Skill_Set> ListUser_Skill_Set(){
        return user_skill_set_repository.findAll();
    }

    @Override
    public void saveUser_Skill_Set (User_Skill_Set u_s) {
        user_skill_set_repository.save(u_s);
    }

    @Override
    public void removeUser_Skill_Set(Long id) {
        user_skill_set_repository.deleteById(id);
    }
    @Override
    public void updateUser_Skill_Set (User_Skill_Set u_s)
    {
        user_skill_set_repository.save(u_s);
    }
    @Override
    public Optional<User_Skill_Set> Find_User_Skill_Set(Long Id) {
        return user_skill_set_repository.findById(Id);
    }
}

package com.example.MyComar_Back.reposotoryInterface;


import com.example.MyComar_Back.Entities.User_Skill_Set;

import java.util.List;
import java.util.Optional;

public interface User_Skill_Set_Repo_Interface {

    void saveUser_Skill_Set( User_Skill_Set u_s);
    void updateUser_Skill_Set( User_Skill_Set u_s);
    List<User_Skill_Set> ListUser_Skill_Set();
    void removeUser_Skill_Set(Long Id);
    Optional<User_Skill_Set> Find_User_Skill_Set(Long Id);
}

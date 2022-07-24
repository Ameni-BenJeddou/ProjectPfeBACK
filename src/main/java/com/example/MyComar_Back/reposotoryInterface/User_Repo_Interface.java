package com.example.MyComar_Back.reposotoryInterface;

import com.example.MyComar_Back.Entities.User;

import java.util.List;
import java.util.Optional;

public interface User_Repo_Interface  {

    void saveUser( User U);
    void updateUser( User c);
    List<User> ListUser();
    void removeUser(Long Id);
    Optional<User> FindById(Long Id);
    Optional<User> FindByIdaccount(Long Id);

}

package com.example.MyComar_Back.ResposotoryImplementaion;

import com.example.MyComar_Back.Entities.User;
import com.example.MyComar_Back.reposotory.User_repository;
import com.example.MyComar_Back.reposotoryInterface.User_Repo_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class User_repo_implementation implements User_Repo_Interface {
    @Autowired

    User_repository user_repository;
    @Override
    public List<User> ListUser(){
        return user_repository.findAll();
    }

    @Override
    public void saveUser (User u) {
        user_repository.save(u);
    }

    @Override
    public void removeUser(Long id) {
        user_repository.deleteById(id);
    }
    @Override
    public void updateUser (User u)
    {
        user_repository.save(u);
    }

    @Override
    public Optional<User> FindById(Long Id) {
        return user_repository.findById(Id);
    }

    @Override
    public Optional<User> FindByIdaccount(Long Id) {
        return user_repository.findById_account(Id);
    }


}

package com.example.MyComar_Back.ResposotoryImplementaion;

import com.example.MyComar_Back.Entities.Spontaneous_application;
import com.example.MyComar_Back.reposotory.Job_Opening_repository;
import com.example.MyComar_Back.reposotory.S_Application_reposotory;
import com.example.MyComar_Back.reposotoryInterface.S_Application_Repo_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class S_Application_repo_implementation implements S_Application_Repo_Interface {
    @Autowired

    S_Application_reposotory s_application_repository;
    @Override
    public void save_S_Application(Spontaneous_application s_application) {
        s_application_repository.save(s_application);
    }

    @Override
    public void update_S_Application(Spontaneous_application s_application) {
        s_application_repository.save(s_application);
    }

    @Override
    public List<Spontaneous_application> List_S_Application() {
        return s_application_repository.findAll();
    }

    @Override
    public void remove_S_Application(Long Id) {
        s_application_repository.deleteById(Id);
    }

    @Override
    public Optional<Spontaneous_application> Find_S_Application(Long Id) {
        return s_application_repository.findById(Id);
    }
}

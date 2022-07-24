package com.example.MyComar_Back.reposotoryInterface;

import com.example.MyComar_Back.Entities.Spontaneous_application;

import java.util.List;
import java.util.Optional;

public interface S_Application_Repo_Interface {
    void save_S_Application( Spontaneous_application s_application);
    void update_S_Application( Spontaneous_application s_application);
    List<Spontaneous_application> List_S_Application();
    void remove_S_Application(Long Id);
    Optional<Spontaneous_application> Find_S_Application(Long Id);
}

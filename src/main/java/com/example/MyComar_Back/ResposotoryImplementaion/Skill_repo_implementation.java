package com.example.MyComar_Back.ResposotoryImplementaion;

import com.example.MyComar_Back.Entities.Skill;
import com.example.MyComar_Back.reposotory.Skill_repository;
import com.example.MyComar_Back.reposotoryInterface.Skill_Repo_Interface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class Skill_repo_implementation implements Skill_Repo_Interface {
    @Autowired

    Skill_repository skill_repository;
    @Override
    public List<Skill> ListSkill(){
        return skill_repository.findAll();
    }

    @Override
    public void saveSkill (Skill s) {
        skill_repository.save(s);
    }

    @Override
    public void removeSkill(Long id) {
        skill_repository.deleteById(id);
    }
    @Override
    public void updateSkill (Skill s)
    {
        skill_repository.save(s);
    }
    @Override
    public Optional<Skill> Find_Skill(Long Id) {
        return skill_repository.findById(Id);
    }
}

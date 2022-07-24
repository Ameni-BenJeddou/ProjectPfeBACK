package com.example.MyComar_Back.reposotoryInterface;

import com.example.MyComar_Back.Entities.Skill;

import java.util.List;
import java.util.Optional;

public interface Skill_Repo_Interface {

    void saveSkill( Skill s);
    void updateSkill( Skill s);

    List<Skill> ListSkill();
    void removeSkill(Long Id);
    Optional<Skill> Find_Skill(Long Id);
}

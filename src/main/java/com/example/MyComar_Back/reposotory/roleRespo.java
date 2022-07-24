package com.example.MyComar_Back.reposotory;

import com.example.MyComar_Back.Entities.Eroles;
import com.example.MyComar_Back.Entities.role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface roleRespo extends JpaRepository<role, Long> {
        role findByName(Eroles name);
        }

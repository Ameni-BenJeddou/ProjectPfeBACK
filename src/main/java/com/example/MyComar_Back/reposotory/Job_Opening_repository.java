package com.example.MyComar_Back.reposotory;

import com.example.MyComar_Back.Entities.Job_Opening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface Job_Opening_repository extends JpaRepository<Job_Opening, Long> {
}

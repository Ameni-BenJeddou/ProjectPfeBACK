package com.example.MyComar_Back.reposotory;

import com.example.MyComar_Back.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountReposotory extends JpaRepository<Account, Long>{
    Account findByemail(String email);
    Optional<Account> findById(Long id);
    Boolean existsByemail(String email);
}
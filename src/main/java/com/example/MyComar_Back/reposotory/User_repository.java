package com.example.MyComar_Back.reposotory;

import com.example.MyComar_Back.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface User_repository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    @Query(value ="select * from User u where u.Id_account_id= ?1",nativeQuery = true)
    Optional<User> findById_account(Long id);

}

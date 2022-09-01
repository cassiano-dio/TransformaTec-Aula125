package com.aula124.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aula124.project.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

    List<User> findByUsername(String username);

    User findById(long id);
    
}

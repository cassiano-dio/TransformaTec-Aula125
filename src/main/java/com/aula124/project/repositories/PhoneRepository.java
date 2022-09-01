package com.aula124.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aula124.project.models.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long>{

    List<Phone> findByPhoneNumber(String phoneNumber);

    @Query(value = "SELECT * FROM phones p WHERE p.c_id = :c_id", nativeQuery = true)
    List<Phone> findByContact(@Param("c_id") long id);
    
}

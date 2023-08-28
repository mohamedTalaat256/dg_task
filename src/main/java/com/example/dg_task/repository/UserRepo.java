package com.example.dg_task.repository;


import com.example.dg_task.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Long > {
    Optional<AppUser> findByUsername(String userName);


    Optional<AppUser> findUserByEmail(String email);


}

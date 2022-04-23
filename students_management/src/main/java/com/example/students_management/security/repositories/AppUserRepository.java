package com.example.students_management.security.repositories;

import com.example.students_management.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String > {
AppUser findByUsername(String username);
}

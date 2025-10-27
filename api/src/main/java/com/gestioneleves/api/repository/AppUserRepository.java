package com.gestioneleves.api.repository;

import com.gestioneleves.api.entity.AppUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    
    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username); 
}

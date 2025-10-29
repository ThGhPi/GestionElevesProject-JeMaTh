package com.gestioneleves.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.AppUser;


@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username); // for authentication

    List<AppUser> findByChildrenId(Long id); // find legal guardians of a student

    Optional <AppUser> findByClassGroupId(Long id); // find head teacher of classGroup

    public List<AppUser> findByTeachingId(Iterable<Long> ids); // find list of teachers by list of teaching ids
}

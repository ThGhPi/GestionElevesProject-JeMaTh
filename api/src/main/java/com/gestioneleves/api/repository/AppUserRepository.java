package com.gestioneleves.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.AppUser;
import com.gestioneleves.api.entity.ClassGroup;

@Repository
public interface AppUserRepository extends ListCrudRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username); // for authentication

    public List<AppUser> findByChildrenId(Long id); // find legal guardians of a student
    public AppUser findByClassGroupId(Long id); // find head teacher of classGroup
    public List<AppUser> findByClassGroup(ClassGroup classGroup); // find list of teachers of a classGroup & use namedQuery
    // public List<AppUser> findByTeachingId(Iterable<Long> ids); // find list of teachers by list of teaching ids

    /* Pour le CRUD */

    // Read
    Optional<AppUser> findById(Long id);
    public List<AppUser> findAll();

    // public List<AppUser> saveAll(List<AppUser> appUsers); // create and update
    public AppUser save(AppUser appuser); // create and update
    public void deleteById(Long id); // delete
}

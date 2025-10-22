package com.gestioneleves.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.Teaching;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeachingRepository extends CrudRepository<Teaching, Long> {
    public List<Teaching> findByTeacherId(Long teacherId); // renvoie les matières d'un professeur
    public List<Teaching> findByClassGroupId(Long classGroupId); // renvoie les matières par classe
    public List<Teaching> findBySubjectName(String subjectName); // renvoie le nom des matières

    Optional<Teaching> findByClassGroupIdAndSubjectName(Long classGroupId, String subjectName);
    // renvoie une seule matière si elle existe, pour un groupe donné et un nom de matière donné.

    /* Pour le CRUD */
    // Read
    public Optional<Teaching> findById(Long id);
    public List<Teaching> findAll();

    public Teaching save(Teaching teaching); // create and update
    public void deleteById(Long id); // delete
}

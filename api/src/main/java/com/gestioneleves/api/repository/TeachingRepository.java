package com.gestioneleves.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.Teaching;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeachingRepository extends JpaRepository<Teaching, Long> {
    List<Teaching> findByTeacherId(Long teacherId); // renvoie les matières d'un professeur
    List<Teaching> findByClassGroupId(Long classGroupId); // renvoie les matières par classe
    List<Teaching> findBySubjectName(String subjectName); // renvoie le nom des matières

    Optional<Teaching> findByClassGroupIdAndSubjectName(Long classGroupId, String subjectName);
    // renvoie une seule matière si elle existe, pour un groupe donné et un nom de matière donné.

    /* Pour le CRUD */
    // Read
}

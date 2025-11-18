package com.gestioneleves.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.Teaching;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeachingRepository extends JpaRepository<Teaching, Long> {
    @Query("""
    SELECT t
    FROM Teaching t
    WHERE 
        t.teacher.role = com.gestioneleves.api.entity.UserRole.TEACHER
        AND (
            lower(t.teacher.firstname) LIKE lower(concat('%', :name, '%'))
            OR lower(t.teacher.lastname) LIKE lower(concat('%', :name, '%'))
        )
""")
List<Teaching> searchTeacherByName(@Param("name") String name);
 // renvoie les matières d'un professeur
    List<Teaching> findByClassGroupId(Long classGroupId); // renvoie les matières par classe
    List<Teaching> findBySubjectName(String subjectName); // renvoie le nom des matières

    Optional<Teaching> findByClassGroupIdAndSubjectName(Long classGroupId, String subjectName);
    // renvoie une seule matière si elle existe, pour un groupe donné et un nom de matière donné.

    /* Pour le CRUD */
    // Read
}

package com.gestioneleves.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.Evaluation;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    // evaluations of a student given a time period
    @Query(
        "Select e from Evaluation e where e.student.id = :studentId and e.teaching.id = :teachingId and e.dateAndTime between :startTime and :endTime")
    public List<Evaluation> findByStudentIdAndTeachingIdAndDateAndTime(
        @Param("studentId") Long studentId,
        @Param("teachingId") Long teachingId,
        @Param("startTime") LocalDateTime startTimme,
        @Param("endTime") LocalDateTime endTime);
    
    List<Evaluation> findByStudentId(Long id); // evaluations of a student
    List<Evaluation> findByTeachingId(Long id); // evaluations of a teaching

}

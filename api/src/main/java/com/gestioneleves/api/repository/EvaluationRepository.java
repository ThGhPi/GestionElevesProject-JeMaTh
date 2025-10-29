package com.gestioneleves.api.repository;

import java.util.Date;
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
        "Select e from Evaluation e where e.student.id = :studentId and e.teaching.id = :teachingId and e.dateAndTime between :startDate and :endDate")
    public List<Evaluation> findByStudentIdAndTeachingIdAndDateAndTime(
        @Param("studentId") Long studentId,
        @Param("teachingId") Long teachingId,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate);
    
    List<Evaluation> findByStudentId(Long id); // evaluations of a student
    List<Evaluation> findByTeachingId(Long id); // evaluations of a teaching

}

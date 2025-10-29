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
        "Select e from Evaluation e where e.student_id = :studentId and e.teaching_id = :teachingId and e.date_and_time beetwen :startDate and :endDate")
    public List<Evaluation> findByStudentIdAndTeachingIdAndDateAndTime(
        @Param("studentId") Long studentId,
        @Param("teachingId") Long teachingId,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate);
    // evaluations of a teaching
    // public List<Evaluation> findByTeachingIdOrderByDateAndTime(Long id);

}

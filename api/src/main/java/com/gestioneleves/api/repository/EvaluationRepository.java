package com.gestioneleves.api.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.Evaluation;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    // evaluations of a student given a time period
    // public List<Evaluation> findByStudentIdAndDateAndTimeOrderByTeachingId(Long id, LocalDate startDate, LocalDate endDate);
    // evaluations of a teaching
    // public List<Evaluation> findByTeachingIdOrderByDateAndTime(Long id);

}

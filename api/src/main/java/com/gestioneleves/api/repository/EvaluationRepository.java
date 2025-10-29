package com.gestioneleves.api.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.Evaluation;

@Repository
public interface EvaluationRepository extends ListCrudRepository<Evaluation, Long> {

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

    // Toutes les évaluations d'un élève (par id élève)
    List<Evaluation> findByStudentId(Long studentId);

    // Toutes les évaluations d'une matière / Teaching (par id Teaching)
    List<Evaluation> findByTeachingId(Long teachingId);

    /* Pour le CRUD */
    // Read
    public Optional<Evaluation> findById(Long id);
    public List<Evaluation> findAll();

    // public List<Evaluation> saveAll(List<Evaluation> evaluations);
    public Evaluation save(Evaluation evaluation); // create and update
    public void deleteById(Long id); // delete
}

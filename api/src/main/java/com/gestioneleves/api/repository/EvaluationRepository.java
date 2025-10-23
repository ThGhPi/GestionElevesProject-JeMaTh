package com.gestioneleves.api.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.Evaluation;

@Repository
public interface EvaluationRepository extends ListCrudRepository<Evaluation, Long> {

    // evaluations of a student given a time period
    // public List<Evaluation> findByStudentIdAndDateAndTimeOrderByTeachingId(Long id, LocalDate startDate, LocalDate endDate);
    // evaluations of a teaching
    // public List<Evaluation> findByTeachingIdOrderByDateAndTime(Long id);

    
    /* Pour le CRUD */
    // Read
    public Optional<Evaluation> findById(Long id);
    public List<Evaluation> findAll();

    // public List<Evaluation> saveAll(List<Evaluation> evaluations);
    public Evaluation save(Evaluation evaluation); // create and update
    public void deleteById(Long id); // delete
}

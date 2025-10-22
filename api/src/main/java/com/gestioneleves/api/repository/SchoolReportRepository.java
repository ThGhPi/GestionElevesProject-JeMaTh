package com.gestioneleves.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.SchoolReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolReportRepository extends CrudRepository<SchoolReport, Long> {
    public List<SchoolReport> findByStudentId(Long studentId); // renvoi les bulletins d'un étudiant
    public List<SchoolReport> findBySchoolPeriodStart(LocalDate schoolPeriodStart); // renvoi les bulletins par période

    public List<SchoolReport> findByStudentIdAndSchoolPeriodStart(Long studentId, LocalDate schoolPeriodStart);


    /* Pour le CRUD */
    // Read
    public Optional<SchoolReport> findById(Long id);
    public List<SchoolReport> findAll();

    public SchoolReport save(SchoolReport schoolReport); // create and update
    public void deleteById(Long id); // delete
}
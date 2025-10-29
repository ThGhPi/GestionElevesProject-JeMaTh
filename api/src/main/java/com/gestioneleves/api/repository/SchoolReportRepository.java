package com.gestioneleves.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.SchoolReport;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface SchoolReportRepository extends JpaRepository<SchoolReport, Long> {
    public List<SchoolReport> findByStudentId(Long studentId); // renvoi les bulletins d'un étudiant
    public List<SchoolReport> findBySchoolPeriodStart(LocalDate schoolPeriodStart); // renvoi les bulletins par période

   // public List<SchoolReport> findByStudentIdAndSchoolPeriodStart(Long studentId, LocalDate schoolPeriodStart);
}
    


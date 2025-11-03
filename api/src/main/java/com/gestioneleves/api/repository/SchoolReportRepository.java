package com.gestioneleves.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.SchoolReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface SchoolReportRepository extends JpaRepository<SchoolReport, Long> {
    
    @Query(
        "Select sr from SchoolReport sr left join fetch sr.schoolReportLines where sr.id = :id"
    )
    Optional<SchoolReport> findByIdWithSchoolReportLines(@Param("id") Long id);

    public List<SchoolReport> findByStudentId(Long studentId); // renvoi les bulletins d'un étudiant
    public List<SchoolReport> findByPeriodStart(LocalDate periodStart); // renvoi les bulletins par période

   // public List<SchoolReport> findByStudentIdAndPeriodStart(Long studentId, LocalDate schoolPeriodStart);
}
    


package com.gestioneleves.api.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.SchoolReport;

@Repository
public interface SchoolReportRepository extends ListCrudRepository<SchoolReport, Long> {
    
}

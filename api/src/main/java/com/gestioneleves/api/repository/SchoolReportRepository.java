package com.gestioneleves.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.SchoolReport;

@Repository
public interface SchoolReportRepository extends CrudRepository<SchoolReport, Long> {
    
}

package com.gestioneleves.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.SchoolReportLine;

@Repository
public interface SchoolReportLineRepository extends CrudRepository<SchoolReportLine, Long> {

}

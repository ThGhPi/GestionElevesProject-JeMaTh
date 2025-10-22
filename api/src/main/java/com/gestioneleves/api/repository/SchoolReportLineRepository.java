package com.gestioneleves.api.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.Registration;
import com.gestioneleves.api.entity.SchoolReportLine;

@Repository
public interface SchoolReportLineRepository extends ListCrudRepository<SchoolReportLine, Long> {
    
    // registration linked to a classGroup for a schoolYear
    public List<SchoolReportLine> findBySchoolReportIdAndSchoolYear(Long classGroupId, String schoolYear);
    public List<SchoolReportLine> findByClassGroupIdOrderBySchoolYear(Long classGroupId);
    
    // registrations of a student in the establishment
    public List<Registration> findByStudentId(Long studentId);

    
    
    
    /* Pour le CRUD */
    // Read
    public List<SchoolReportLine> findAll();

    public List<SchoolReportLine> saveAll(List<SchoolReportLine> schoolReportLines);
    public SchoolReportLine save(SchoolReportLine schoolReportLine); // create and update
    public void deleteById(Long id); // delete
}

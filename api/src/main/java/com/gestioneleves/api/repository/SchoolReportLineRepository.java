package com.gestioneleves.api.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.SchoolReportLine;
import com.gestioneleves.api.entity.SchoolReportLinePK;

@Repository
public interface SchoolReportLineRepository extends JpaRepository<SchoolReportLine, SchoolReportLinePK> {
    
    // registration linked to a classGroup for a schoolYear
    // public List<SchoolReportLine> findBySchoolReportIdAndSchoolPeriodStart(Long schoolReportId, String schoolPeriodStart);
    // public List<SchoolReportLine> findBySchoolReportIdOrderBySchoolPeriodStart(Long schoolReportId);
    
    // // registrations of a student in the establishment
    // public List<SchoolReportLine> findByTeachingIdAndSchoolPeriodStart(Long teachingId, String schoolPeriodStart);
    // public List<SchoolReportLine> findByTeachingIdOrderBySchoolPeriodStart(Long teachingId);

    /* Pour le CRUD */
    // Read
}

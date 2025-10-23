package com.gestioneleves.api.service;


import com.gestioneleves.api.entity.SchoolReport;
import com.gestioneleves.api.repository.SchoolReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolReportService {
    private final SchoolReportRepository schoolReports;

    public SchoolReportService(SchoolReportRepository schoolReports) {
        this.schoolReports = schoolReports;
    }

    public List<SchoolReport> getSchoolReports() {
        return schoolReports.findAll();
    }
    public Optional<SchoolReport> getSchoolReport(Long id) {
        return schoolReports.findById(id);
    }

    public SchoolReport saveSchoolReport(SchoolReport schoolReport) {
        return schoolReports.save(schoolReport);
    }

    public void deleteSchoolReport(Long id) {
        schoolReports.deleteById(id);
    }
}
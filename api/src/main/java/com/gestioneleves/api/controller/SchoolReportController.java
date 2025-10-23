package com.gestioneleves.api.controller;

import com.gestioneleves.api.entity.SchoolReport;
import com.gestioneleves.api.service.SchoolReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/school-reports")
public class SchoolReportController {

    @Autowired
    private SchoolReportService service;

    @GetMapping
    public List<SchoolReport> getSchoolReports() {
        return service.getSchoolReports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolReport> getSchoolReport(@PathVariable Long id) {
        return service.getSchoolReport(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SchoolReport create(@RequestBody SchoolReport schoolReport) {
        return service.saveSchoolReport(schoolReport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolReport> update(@PathVariable Long id, @RequestBody SchoolReport schoolReport) {
        return service.getSchoolReport(id)
                .map(existing -> {
                    schoolReport.setId(id);
                    return ResponseEntity.ok(service.saveSchoolReport(schoolReport));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.getSchoolReport(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteSchoolReport(id);
        return ResponseEntity.noContent().build();
    }
}
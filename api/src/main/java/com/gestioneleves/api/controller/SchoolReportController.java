package com.gestioneleves.api.controller;

import com.gestioneleves.api.dto.SchoolReportDTO;
import com.gestioneleves.api.service.SchoolReportService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/school-reports")
@RequiredArgsConstructor
public class SchoolReportController {

    private final SchoolReportService service;

    @GetMapping
    public List<SchoolReportDTO> getSchoolReports() {
        return service.getSchoolReports();
    }

    @GetMapping("/{id}")
    public SchoolReportDTO getSchoolReport(@PathVariable Long id) {
        return service.getSchoolReport(id);
    }

    @PostMapping
    public SchoolReportDTO create(@RequestBody SchoolReportDTO schoolReportDto) {
        SchoolReportDTO created = service.saveSchoolReport(null, schoolReportDto);
        if ((created.getOverallAverage() == null ? 0.0 : created.getOverallAverage()) == -1) {
            return service.calculateAverages(created.getId());
        } else { return created; }
    }

    @PutMapping("/{id}")
    public SchoolReportDTO update(@PathVariable Long id, @RequestBody SchoolReportDTO schoolReportDto) {
        return service.saveSchoolReport(id, schoolReportDto);          
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteSchoolReport(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{id}")
    public List<SchoolReportDTO> getByStudentId(@PathVariable Long id) {
        return service.findByStudent(id);
    }

    @GetMapping("/period/{startDate}")
    public List<SchoolReportDTO> getByPeriodStart(@PathVariable LocalDate startDate) {
        return service.findByPeriod(startDate);
    }
    
    
}
package com.gestioneleves.api.controller;

import com.gestioneleves.api.dto.SchoolReportDTO;
import com.gestioneleves.api.service.SchoolReportService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
        return service.saveSchoolReport(null, schoolReportDto);
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
    public List<SchoolReportDTO> getByStudentId(@RequestParam Long id) {
        return service.findByStudent(id);
    }

    @GetMapping("/period")
    public List<SchoolReportDTO> getByPeriodStart(@RequestParam LocalDate date) {
        return service.findByPeriod(date);
    }
    
    
}
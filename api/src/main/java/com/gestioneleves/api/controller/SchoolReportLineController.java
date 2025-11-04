package com.gestioneleves.api.controller;

import com.gestioneleves.api.dto.SchoolReportLineDTO;
import com.gestioneleves.api.entity.SchoolReportLinePK;
import com.gestioneleves.api.service.SchoolReportLineService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/school-report-lines")
@RequiredArgsConstructor
public class SchoolReportLineController {

    private final SchoolReportLineService service;

    @GetMapping
    public List<SchoolReportLineDTO> getSchoolReportLines() { return service.getSchoolReportLines();}

    @GetMapping("/{id}")
    public SchoolReportLineDTO getSchoolReportLine(@PathVariable SchoolReportLinePK id) {
        return service.getSchoolReportLine(id);}

    @PostMapping
    public SchoolReportLineDTO create(@RequestBody SchoolReportLineDTO schoolReportLineDTO) {
        return service.saveSchoolReportLine(schoolReportLineDTO);
    }

    @PutMapping("/{id}")
    public SchoolReportLineDTO update(@PathVariable SchoolReportLinePK id, @RequestBody SchoolReportLineDTO schoolReportLineDTO) {
        schoolReportLineDTO.setId(id);
        return service.saveSchoolReportLine(schoolReportLineDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable SchoolReportLinePK id) {
        service.deleteSchoolReport(id);
    }
}

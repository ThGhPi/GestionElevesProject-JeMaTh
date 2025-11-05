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
    public List<SchoolReportLineDTO> getSchoolReportLines() { return service.getSchoolReportLines(); }

    @GetMapping("/school-report/{schoolReportId}/teaching/{teachingId}")
    public SchoolReportLineDTO getSchoolReportLine(@PathVariable Long schoolReportId, @PathVariable Long teachingId) {
        return service.getSchoolReportLine(new SchoolReportLinePK(schoolReportId, teachingId));
    }

    @PostMapping
    public SchoolReportLineDTO create(@RequestBody SchoolReportLineDTO schoolReportLineDTO) {
        return service.saveSchoolReportLine(schoolReportLineDTO);
    }

    @PutMapping("/school-report/{schoolReportId}/teaching/{teachingId}")
    public SchoolReportLineDTO update(@PathVariable Long schoolReportId, @PathVariable Long teachingId, @RequestBody SchoolReportLineDTO schoolReportLineDTO) {
        schoolReportLineDTO.setId(new SchoolReportLinePK(schoolReportId, teachingId));
        return service.saveSchoolReportLine(schoolReportLineDTO);
    }

    @DeleteMapping("/school-report/{schoolReportId}/teaching/{teachingId}")
    public void delete(@PathVariable Long schoolReportId, @PathVariable Long teachingId) {
        service.deleteSchoolReport(new SchoolReportLinePK(schoolReportId, teachingId));
    }
}

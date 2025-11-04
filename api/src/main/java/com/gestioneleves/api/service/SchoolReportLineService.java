package com.gestioneleves.api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gestioneleves.api.dto.EvaluationDTO;
import com.gestioneleves.api.dto.SchoolReportDTO;
import com.gestioneleves.api.dto.SchoolReportLineDTO;
import com.gestioneleves.api.entity.SchoolReportLine;
import com.gestioneleves.api.entity.SchoolReportLinePK;
import com.gestioneleves.api.repository.SchoolReportLineRepository;
import com.gestioneleves.api.service.mapper.SchoolReportLineMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolReportLineService {
    private final SchoolReportLineRepository repository;
    private final SchoolReportLineMapper mapper;
    private final SchoolReportService schoolReportService;
    private final EvaluationService evaluationService;


    public List<SchoolReportLineDTO> getSchoolReportLines() {
        return repository.findAll()
            .stream()
            .map(mapper::toDto)
            .toList();
    }

    public SchoolReportLineDTO getSchoolReportLine(SchoolReportLinePK id) {
        SchoolReportLine schoolReportLine = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cette Ligne n'existe pas"));
        return mapper.toDto(schoolReportLine);
    }

    public SchoolReportLineDTO saveSchoolReportLine(SchoolReportLineDTO schoolReportLineDTO) {
        return mapper.toDto(repository.save(mapper.toEntity(schoolReportLineDTO)));
    }

    public void deleteSchoolReport(SchoolReportLinePK id) {
        repository.deleteById(id);
    }

    public SchoolReportLineDTO calculateTeachingAverage(SchoolReportLinePK id) {
        Long teachingId = id.getTeachingId();
        Long schoolReportId = id.getSchoolReportId();
        SchoolReportLineDTO schoolReportLineDTO = getSchoolReportLine(id);
        SchoolReportDTO schoolReportDTO = schoolReportService.getSchoolReport(schoolReportId);
        LocalDate periodStart = schoolReportDTO.getPeriodStart();
        LocalDate periodEnd = schoolReportDTO.getPeriodEnd();
        Long studentId = schoolReportDTO.getStudentDTO().getId();
        List<EvaluationDTO> evaluations = evaluationService.getEvaluationsByStudentAndTeachingAndPeriod(studentId, teachingId, periodStart, periodEnd);
        Double totalWeightedNotes = evaluations.stream()
            .mapToDouble(e -> e.getNote() * e.getWeight())
            .sum();
        Double totalWeight = evaluations.stream()
            .mapToDouble(EvaluationDTO::getWeight)
            .sum();
        Double teachingAverage = totalWeight == 0 ? null : totalWeightedNotes / totalWeight;
        schoolReportLineDTO.setTeachingAverage(teachingAverage);
        return mapper.toDto(repository.save(mapper.toEntity(schoolReportLineDTO)));
    }
}
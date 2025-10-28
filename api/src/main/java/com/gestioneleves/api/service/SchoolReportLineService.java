package com.gestioneleves.api.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gestioneleves.api.dto.EvaluationDTO;
import com.gestioneleves.api.dto.SchoolReportDTO;
import com.gestioneleves.api.dto.SchoolReportLineDTO;
import com.gestioneleves.api.entity.Evaluation;
import com.gestioneleves.api.entity.SchoolReport;
import com.gestioneleves.api.entity.SchoolReportLine;
import com.gestioneleves.api.entity.SchoolReportLinePK;
import com.gestioneleves.api.repository.SchoolReportLineRepository;
import com.gestioneleves.api.repository.SchoolReportRepository;
import com.gestioneleves.api.service.mapper.SchoolReportLineMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolReportLineService {
    private final SchoolReportLineRepository repository;
    private final SchoolReportService schoolReportService;
    private final SchoolReportLineMapper mapper;
    private final EvaluationService evaluationService;


    public List<SchoolReportLineDTO> getSchoolReports() {
        return repository.findAll()
            .stream()
            .map(mapper::toDto)
            .toList();
    }

    public SchoolReportLineDTO getSchoolReportLine(SchoolReportLinePK id) {
        SchoolReportLine schoolReportLine = repository.findById(id)
            .orElseThrow();
        return mapper.toDto(schoolReportLine);
    }

    public SchoolReportLineDTO saveSchoolReportLine(SchoolReportLine schoolReportLine) {
        return mapper.toDto(repository.save(schoolReportLine));
    }

    public void deleteSchoolReport(SchoolReportLinePK id) {
        repository.deleteById(id);
    }

    public SchoolReportLineDTO calculateTeachingAverage(SchoolReportLinePK id) {
        Long teachingId = id.getTeachingId();
        Long schoolReportId = id.getSchoolReportId();
        SchoolReportLineDTO schoolReportLineDTO = getSchoolReportLine(id);
        SchoolReportDTO schoolReportDTO = schoolReportService.getSchoolReport(schoolReportId);
        Date periodStart = schoolReportDTO.getSchoolPeriodStart();
        Date periodEnd = schoolReportDTO.getSchoolPeriodEnd();
        Long studentId = schoolReportDTO.getStudentDTO().getId();
        List<EvaluationDTO> evaluations = evaluationService.getEvaluationByStudentAndTeachingAndPeriod(studentId, teachingId, periodStart, periodEnd);
        Double totalWeightedNotes = evaluations.stream()
            .mapToDouble(e -> e.getNote() * e.getWeight())
            .sum();
        Double totalWeight = evaluations.stream()
            .mapToDouble(EvaluationDTO::getWeight)
            .sum();
        Double teachingAverage = totalWeight == 0 ? 0 : totalWeightedNotes / totalWeight;
        schoolReportLineDTO.setTeachingAverage(teachingAverage);
        return mapper.toDto(repository.save(mapper.toEntity(schoolReportLineDTO)));
    }
}

package com.gestioneleves.api.service;

import com.gestioneleves.api.dto.RegistrationDTO;
import com.gestioneleves.api.dto.SchoolReportDTO;
import com.gestioneleves.api.entity.SchoolReport;
import com.gestioneleves.api.entity.SchoolReportLine;
import com.gestioneleves.api.entity.SchoolReportLinePK;
import com.gestioneleves.api.repository.SchoolReportRepository;
import com.gestioneleves.api.repository.StudentRepository;
import com.gestioneleves.api.service.mapper.SchoolReportMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SchoolReportService {

    private final SchoolReportRepository repository;
    private final SchoolReportMapper mapper;
    private final SchoolReportLineService srlService;
    private final RegistrationService registrationService;
    private final StudentRepository studentRepository;

    public List<SchoolReportDTO> getSchoolReports() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public SchoolReportDTO getSchoolReport(Long id) {
        SchoolReport schoolReport = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bulletin inexistant"));
        return mapper.toDto(schoolReport);
    }

    @Transactional
    public SchoolReportDTO saveSchoolReport(Long id, SchoolReportDTO dto) {
        SchoolReport bulletin = mapper.toEntity(dto, studentRepository);
        SchoolReport saved = repository.save(bulletin);
        if (id == null) {
            String schoolYear = getSchoolYear(dto);
            List<RegistrationDTO> registrations = registrationService.getRegistrationsByStudentAndYear(saved.getStudent().getId(), schoolYear);
            if (registrations.size() == 1) {
                Long classGroupId = registrations.getFirst().getId().getClassGroupId()
            }
        }
        return mapper.toDto(saved);
    }

    public void deleteSchoolReport(Long id) {
        repository.deleteById(id);
    }

    public List<SchoolReportDTO> findByStudent(Long id) {
        return repository.findByStudentId(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors
                        .toList());
    }

    public List<SchoolReportDTO> findByPeriod(LocalDate date) {
        return repository.findByPeriodStart(date)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors
                        .toList());
    }

    public SchoolReportDTO calculateAverages(Long id) {
        SchoolReportDTO schoolReportDTO = getSchoolReport(id);
        List<SchoolReportLinePK> lines = schoolReportDTO.getSchoolReportLinesIds();
        if (lines == null || lines.isEmpty()) {
            throw new RuntimeException("Pas de lignes attachées au bulletin !");
        }
        Double overallAverage = lines == null ? null
                : (lines.stream()
                        .mapToDouble(pk -> {
                            return srlService.getSchoolReportLine(pk).getTeachingAverage() == null
                                    ? srlService.calculateTeachingAverage(pk).getTeachingAverage()
                                    : srlService.getSchoolReportLine(pk).getTeachingAverage();

                        }))
                        .sum();
        schoolReportDTO.setOverallAverage(lines.size() == 0 ? 0 : overallAverage / lines.size());
        return mapper.toDto(repository.save(mapper.toEntity(schoolReportDTO, studentRepository)));
    }

    public String getSchoolYear(SchoolReportDTO schoolReportDTO) {
        LocalDate periodStart = schoolReportDTO.getPeriodStart();
        LocalDate schoolYearChange = LocalDate.of(periodStart.getYear(), 07, 31);
        if (periodStart.isAfter(schoolYearChange)) {
            return ((Integer) periodStart.plusYears(1).getYear()).toString();
        } else {
            return ((Integer) periodStart.getYear()).toString();
        }
    }
}
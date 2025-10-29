package com.gestioneleves.api.service;


import com.gestioneleves.api.dto.SchoolReportDTO;
import com.gestioneleves.api.entity.SchoolReport;
import com.gestioneleves.api.repository.SchoolReportRepository;
import com.gestioneleves.api.service.mapper.SchoolReportMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolReportService {

    private final SchoolReportRepository schoolReportRepository;
    private final SchoolReportMapper mapper;


    public List<SchoolReportDTO> getSchoolReports() {
        return schoolReportRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors
                .toList());
    }
    public SchoolReportDTO getSchoolReport(Long id) {
             SchoolReport schoolReport= schoolReportRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Bulletin inexistant"));
        return mapper.toDto(schoolReport);
    }

    public SchoolReportDTO saveSchoolReport(SchoolReportDTO dto) {
            SchoolReport bulletin = mapper.toEntity(dto);
            SchoolReport saved = schoolReportRepository.save(bulletin);
        return mapper.toDto(saved);
    }

    public void deleteSchoolReport(Long id) {
        schoolReportRepository.deleteById(id);
    }

    public List<SchoolReportDTO> findByStudent(Long id){
        return schoolReportRepository.findByStudentId(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors
                .toList());
    }

    public List<SchoolReportDTO> findByPeriod(LocalDate date){
     return schoolReportRepository.findBySchoolPeriodStart(date)
            .stream()
            .map(mapper::toDto)
            .collect(Collectors
            .toList());
    }
}
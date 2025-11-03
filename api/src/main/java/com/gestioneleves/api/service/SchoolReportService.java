package com.gestioneleves.api.service;


import com.gestioneleves.api.dto.SchoolReportDTO;
import com.gestioneleves.api.entity.SchoolReport;
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
    private final StudentRepository studentRepository;


    public List<SchoolReportDTO> getSchoolReports() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList()); 
    }
    public SchoolReportDTO getSchoolReport(Long id) {
             SchoolReport schoolReport= repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Bulletin inexistant"));
        return mapper.toDto(schoolReport);
    }

    @Transactional
    public SchoolReportDTO saveSchoolReport(SchoolReportDTO dto) {
            SchoolReport bulletin = mapper.toEntity(dto, studentRepository);
            SchoolReport saved = repository.save(bulletin);
        return mapper.toDto(saved);
    }

    public void deleteSchoolReport(Long id) {
        repository.deleteById(id);
    }

    public List<SchoolReportDTO> findByStudent(Long id){
        return repository.findByStudentId(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors
                .toList());
    }

    public List<SchoolReportDTO> findByPeriod(LocalDate date){
     return repository.findByPeriodStart(date)
            .stream()
            .map(mapper::toDto)
            .collect(Collectors
            .toList());
    }

    
}
package com.gestioneleves.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gestioneleves.api.repository.SchoolReportLineRepository;
import com.gestioneleves.api.service.mapper.SchoolReportLineMapper;
import com.gestioneleves.api.dto.SchoolReportLineDTO;
import com.gestioneleves.api.entity.SchoolReportLine;
import com.gestioneleves.api.entity.SchoolReportLinePK;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchoolReportLineService {

    private final SchoolReportLineRepository repo;
    private final SchoolReportLineMapper mapper;

    public List<SchoolReportLineDTO> getSchoolReportLine(){
    return repo.findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors
            .toList());
   } 

   public SchoolReportLineDTO getById(SchoolReportLinePK id){
        SchoolReportLine schl = repo.findById(id)
        .orElseThrow(() -> new RuntimeException("Cette Ligne n'existe pas"));
    return mapper.toDto(schl);
   }

}


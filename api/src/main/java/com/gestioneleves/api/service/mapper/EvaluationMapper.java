package com.gestioneleves.api.service.mapper;

import com.gestioneleves.api.dto.EvaluationDTO;
import com.gestioneleves.api.entity.Evaluation;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EvaluationMapper {
    
    @Mapping(target = "teachingDTO", source = "teaching")
    @Mapping(target = "studentDTO", source = "student")
    EvaluationDTO toDto(Evaluation entity);

    @Mapping(target = "teaching", source = "teachingDTO")
    @Mapping(target = "student", source = "studentDTO")
    Evaluation toEntity(EvaluationDTO dto);
}
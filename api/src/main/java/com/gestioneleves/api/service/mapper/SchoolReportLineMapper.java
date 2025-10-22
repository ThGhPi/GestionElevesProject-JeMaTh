package com.gestioneleves.api.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.gestioneleves.api.dto.SchoolReportLineDTO;
import com.gestioneleves.api.entity.SchoolReportLine;

@Mapper(
    componentModel = "spring",
    uses = {TeachingMapper.class, SchoolReportMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SchoolReportLineMapper {

    @Mapping(target = "teachingDto", source = "teaching")
    @Mapping(target = "schoolReportDto", source = "schoolReport")
    SchoolReportLineDTO toDto(SchoolReportLine entity);

    @Mapping(target = "teaching", source = "teachingDto")
    @Mapping(target = "schoolReport", source = "schoolReportDto")
    SchoolReportLine toEntity(SchoolReportLineDTO dto);
}
 
package com.gestioneleves.api.service.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.gestioneleves.api.dto.SchoolReportLineDTO;
import com.gestioneleves.api.entity.SchoolReport;
import com.gestioneleves.api.entity.SchoolReportLine;
import com.gestioneleves.api.entity.Teaching;
import com.gestioneleves.api.repository.SchoolReportRepository;
import com.gestioneleves.api.repository.TeachingRepository;

@Mapper(
    componentModel = "spring",
    uses = {TeachingMapper.class, SchoolReportMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SchoolReportLineMapper {

    SchoolReportLineDTO toDto(SchoolReportLine entity);

    @Mapping(source = "id.schoolReportId", target = "schoolReport", qualifiedByName = "mapsPkToSchoolReport")
    @Mapping(source = "id.teachingId", target = "teaching", qualifiedByName = "mapsPkToTeaching")
    SchoolReportLine toEntity(SchoolReportLineDTO dto,
        @Context SchoolReportRepository schoolReportRepository,
        @Context TeachingRepository teachingRepository);

    @Named("mapsPkToSchoolReport")
    default SchoolReport mapsPkToSchoolReport(Long schoolReportId,
        @Context SchoolReportRepository schoolReportRepository) {
        return (schoolReportRepository.getReferenceById(schoolReportId));
    }

    @Named("mapsPkToTeaching")
    default Teaching mapsPkToTeaching(Long teachingId,
        @Context TeachingRepository teachingRepository) {
        return (teachingRepository.getReferenceById(teachingId));
    }
}
 
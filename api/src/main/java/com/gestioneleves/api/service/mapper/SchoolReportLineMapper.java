package com.gestioneleves.api.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;



@Mapper(
    componentModel = "spring",
    uses = {TeachingMapper.class, SchoolReportMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SchoolReportLineMapper {

    
}
 
package com.gestioneleves.api.service.mapper;

import com.gestioneleves.api.dto.RegistrationDTO;
import com.gestioneleves.api.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegistrationMapper {
    @Mapping(target = "studentDTO", source = "student")
    @Mapping(target = "classGroupDTO", source = "classGroup")
    RegistrationDTO toDto(Registration entity);

    @Mapping(target = "student", source = "studentDTO")
    @Mapping(target = "classGroup", source = "classGroupDTO")
    Registration toEntity(RegistrationDTO dto);

}
package com.gestioneleves.api.service.mapper;

import com.gestioneleves.api.dto.RegistrationDTO;
import com.gestioneleves.api.entity.*;
import com.gestioneleves.api.repository.ClassGroupRepository;
import com.gestioneleves.api.repository.StudentRepository;

import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegistrationMapper {
    RegistrationDTO toDto(Registration entity);

    @Mapping(target = "student", source = "id.studentId", qualifiedByName = "dtoPKtoStudent")
    @Mapping(target = "classGroup", source = "id.classGroupId", qualifiedByName = "dtoPKtoClassGroup")
    Registration toEntity(RegistrationDTO dto,
        @Context StudentRepository studentRepository,
        @Context ClassGroupRepository classGroupRepository);

    @Named("dtoPKtoStudent")
    default Student dtoPKtoStudent(Long studentId, @Context StudentRepository studentRepository) {
        return studentRepository.getReferenceById(studentId);
    }

    @Named("dtoPKtoClassGroup")
    default ClassGroup dtoPKtoStudent(Long classGroupId, @Context ClassGroupRepository classGroupRepository) {
        return classGroupRepository.getReferenceById(classGroupId);
    }


}
package com.gestioneleves.api.service.mapper;

import com.gestioneleves.api.dto.AppUserDTOCreateAdmin;
import com.gestioneleves.api.dto.AppUserDTOResAdm;
import com.gestioneleves.api.entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppUserMapper {

    @Mappings({
        @Mapping(target = "firstname", source = "firstname"),
        @Mapping(target = "lastname", source = "lastname"),
        @Mapping(target = "email", source = "email"),
        @Mapping(target = "username", source = "username"),
        @Mapping(target = "password", source = "password"),
        @Mapping(target = "phone_number", source = "phone_number"),
        @Mapping(target = "postal_address", source = "postal_address"),
        @Mapping(target = "role", expression = "java(com.gestioneleves.api.entity.UserRole.valueOf(dto.getRole()))")
    })
    AppUser toEntity(AppUserDTOCreateAdmin dto);

    AppUserDTOResAdm toResAdm(AppUser entity);
}

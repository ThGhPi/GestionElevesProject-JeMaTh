package com.gestioneleves.api.service.mapper;

import org.mapstruct.*;
import com.gestioneleves.api.dto.AppUserDTO; 
import com.gestioneleves.api.entity.AppUser;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AppUserMapper {

                  
    @Mapping(target = "password", ignore = true)            
    @Mapping(target = "studentsUnderCare", ignore = true)   
    @Mapping(target = "teachings", ignore = true)           
    AppUser toEntity(AppUserDTO dto);

    
    AppUserDTO toDto(AppUser entity);

    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "studentsUnderCare", ignore = true)
    @Mapping(target = "teachings", ignore = true)
    void updateEntityFromDto(AppUserDTO dto, @MappingTarget AppUser entity);
}

  
package com.gestioneleves.api.service.mapper;

import com.gestioneleves.api.dto.ClassGroupDTO;
import com.gestioneleves.api.entity.*;
import org.mapstruct.*;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
    componentModel = "spring",
    uses = {TeachingMapper.class, RegistrationMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ClassGroupMapper {

    @Mapping(target = "teachingsIds", source = "teachings", qualifiedByName = "mapTeachingsToIds")
    @Mapping(target = "registrationsIds", source = "registrations", qualifiedByName = "mapRegistrationsToIds")
    ClassGroupDTO toDto(ClassGroup entity);

    @Mapping(target = "teachings", source = "teachingsIds", qualifiedByName = "mapIdsToTeachings")
    @Mapping(target = "registrations", source = "registrationsIds", qualifiedByName = "mapIdsToRegistrations")
    ClassGroup toEntity(ClassGroupDTO dto);

    // ---------- Méthodes utilitaires ----------
    @Named("mapTeachingsToIds")
    default List<Long> mapTeachingsToIds(List<Teaching> teachings) {
        return teachings == null ? null :
            teachings.stream()
                     .map(Teaching::getId)
                     .collect(Collectors.toList());
    }

    @Named("mapIdsToTeachings")
    default List<Teaching> mapIdsToTeachings(List<Long> ids) {
        return ids == null ? null :
            ids.stream()
               .map(id -> {
                   Teaching t = new Teaching();
                   t.setId(id);
                   return t;
               })
               .collect(Collectors.toList());
    }

    @Named("mapRegistrationsToIds")
    default List<Long> mapRegistrationsToIds(List<Registration> registrations) {
        return registrations == null ? null :
            registrations.stream()
                         .map(r -> r.getId() != null ? r.getId().getStudentId() : null)
                         .collect(Collectors.toList());
    }

    @Named("mapIdsToRegistrations")
    default List<Registration> mapIdsToRegistrations(List<Long> ids) {
        return ids == null ? null :
            ids.stream()
               .map(id -> {
                   Registration r = new Registration();
                   r.setId(new RegistrationId(id, null));
                   return r;
               })
               .collect(Collectors.toList());
    }
}

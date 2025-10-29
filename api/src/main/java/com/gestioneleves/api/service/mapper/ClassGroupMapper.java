package com.gestioneleves.api.service.mapper;

import com.gestioneleves.api.dto.ClassGroupDTO;
import com.gestioneleves.api.entity.*;
import org.mapstruct.*;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = { TeachingMapper.class,
        RegistrationMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClassGroupMapper {

    @Mapping(target = "headTeacherId", source = "headTeacher.id")
    @Mapping(target = "teachingsIds", source = "teachings", qualifiedByName = "mapTeachingsToIds")
    @Mapping(target = "registrationsIds", source = "registrations", qualifiedByName = "mapRegistrationsToIds")
    ClassGroupDTO toDto(ClassGroup entity);

    @Mapping(target = "headTeacher", source = "headTeacherId", qualifiedByName = "mapIdsToHeadTeacher")
    @Mapping(target = "teachings", source = "teachingsIds", qualifiedByName = "mapIdsToTeachings")
    @Mapping(target = "registrations", source = "registrationsIds", qualifiedByName = "mapIdsToRegistrations")
    ClassGroup toEntity(ClassGroupDTO dto);

    // ---------- Méthodes utilitaires ----------
    @Named("mapIdsToHeadTeacher")
    default AppUser map(Long id) {
        if (id == null)
            return null;
        AppUser appUser = new AppUser();
        appUser.setId(id); // Must exist
        return appUser;
    }

    @Named("mapTeachingsToIds")
    default List<Long> mapTeachingsToIds(List<Teaching> teachings) {
        return teachings == null ? null
                : teachings.stream()
                        .map(Teaching::getId)
                        .collect(Collectors.toList());
    }

    @Named("mapIdsToTeachings")
    default List<Teaching> mapIdsToTeachings(List<Long> ids) {
        return ids == null ? null
                : ids.stream()
                        .map(id -> {
                            Teaching t = new Teaching();
                            t.setId(id);
                            return t;
                        })
                        .collect(Collectors.toList());
    }

    @Named("mapRegistrationsToIds")
    default List<RegistrationPK> mapRegistrationsToIds(List<Registration> registrations) {
        return registrations == null ? null
                : registrations.stream()
                        .map(r -> r.getId() != null ? r.getId() : null)
                        .collect(Collectors.toList());
    }

    @Named("mapIdsToRegistrations")
    default List<Registration> mapIdsToRegistrations(List<RegistrationPK> ids) {
        return ids == null ? null
                : ids.stream()
                        .map(id -> {
                            Registration r = new Registration();
                            r.setId(id);
                            return r;
                        })
                        .collect(Collectors.toList());
    }
}

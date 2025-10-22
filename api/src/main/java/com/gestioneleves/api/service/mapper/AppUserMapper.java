package com.gestioneleves.api.service.mapper;

import com.gestioneleves.api.dto.AppUserDTO;
import com.gestioneleves.api.entity.AppUser;
import com.gestioneleves.api.entity.Student;
import com.gestioneleves.api.entity.Teaching;
import org.mapstruct.*;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AppUserMapper {

    @Mapping(target = "teachingsIds", source = "teachings", qualifiedByName = "mapTeachingsToIds")
    @Mapping(target = "childrenIds", source = "children", qualifiedByName = "mapStudentsToIds")
    AppUserDTO toDto(AppUser entity);

    @Mapping(target = "teachings", source = "teachingsIds", qualifiedByName = "mapIdsToTeachings")
    @Mapping(target = "children", source = "childrenIds", qualifiedByName = "mapIdsToStudents")
    AppUser toEntity(AppUserDTO dto);

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

    @Named("mapStudentsToIds")
    default List<Long> mapStudentsToIds(List<Student> students) {
        return students == null ? null :
            students.stream()
                     .map(Student::getId)
                     .collect(Collectors.toList());
    }

    @Named("mapIdsToStudents")
    default List<Student> mapIdsToStudents(List<Long> ids) {
        return ids == null ? null :
            ids.stream()
               .map(id -> {
                   Student s = new Student();
                   s.setId(id);
                   return s;
               })
               .collect(Collectors.toList());
    }
}

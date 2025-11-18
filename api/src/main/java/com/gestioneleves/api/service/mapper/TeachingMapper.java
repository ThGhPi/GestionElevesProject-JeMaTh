package com.gestioneleves.api.service.mapper;

import com.gestioneleves.api.dto.TeachingDTO;
import com.gestioneleves.api.entity.*;
import com.gestioneleves.api.repository.AppUserRepository;
import com.gestioneleves.api.repository.ClassGroupRepository;

import org.mapstruct.*;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
    componentModel = "spring",
    uses = {
        ClassGroupMapper.class,
        AppUserMapper.class,
        SchoolReportLineMapper.class
    },
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TeachingMapper {

    // ============================
    //       ENTITY → DTO
    // ============================
    @Mapping(target = "evaluationsIds", 
             source = "evaluations", 
             qualifiedByName = "mapEvaluationsToIds")

    @Mapping(target = "classGroupId", 
             source = "classGroup.id")

    // Full name du professeur
    @Mapping(
        target = "teacherName",
        expression = "java(entity.getTeacher().getFirstname() + \" \" + entity.getTeacher().getLastname())"
    )

    @Mapping(target = "schoolReportLinesIds", 
             source = "schoolReportLines", 
             qualifiedByName = "mapSchoolReportLinesToIds")
    TeachingDTO toDto(Teaching entity);



    // ============================
    //       DTO → ENTITY
    // ============================
    @Mapping(target = "teacher", ignore = true) // conversion impossible depuis teacherName (String)

    @Mapping(target = "evaluations", 
             source = "evaluationsIds", 
             qualifiedByName = "mapIdsToEvaluations")

    @Mapping(target = "classGroup", 
             source = "classGroupId", 
             qualifiedByName = "mapIdToClassGroup")

    @Mapping(target = "schoolReportLines", 
             source = "schoolReportLinesIds", 
             qualifiedByName = "mapIdsToSchoolReportLines")
    Teaching toEntity(
        TeachingDTO dto,
        @Context ClassGroupRepository classGroupRepository,
        @Context AppUserRepository appUserRepository
    );



    // ============================
    //          HELPERS
    // ============================

    @Named("mapIdToClassGroup")
    default ClassGroup mapIdToClassGroup(
        Long classGroupId,
        @Context ClassGroupRepository repo
    ) {
        return classGroupId == null ? null : repo.getReferenceById(classGroupId);
    }


    @Named("mapEvaluationsToIds")
    default List<Long> mapEvaluationsToIds(List<Evaluation> evaluations) {
        return evaluations == null ? null :
            evaluations.stream()
                .map(Evaluation::getId)
                .collect(Collectors.toList());
    }


    @Named("mapIdsToEvaluations")
    default List<Evaluation> mapIdsToEvaluations(List<Long> ids) {
        return ids == null ? null :
            ids.stream().map(id -> {
                Evaluation e = new Evaluation();
                e.setId(id);
                return e;
            }).collect(Collectors.toList());
    }


    @Named("mapSchoolReportLinesToIds")
    default List<SchoolReportLinePK> mapSchoolReportLinesToIds(List<SchoolReportLine> lines) {
        return lines == null ? null :
            lines.stream()
                .map(SchoolReportLine::getId)
                .collect(Collectors.toList());
    }


    @Named("mapIdsToSchoolReportLines")
    default List<SchoolReportLine> mapIdsToSchoolReportLines(List<SchoolReportLinePK> ids) {
        return ids == null ? null :
            ids.stream().map(id -> {
                SchoolReportLine l = new SchoolReportLine();
                l.setId(id);
                return l;
            }).collect(Collectors.toList());
    }
}



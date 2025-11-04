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
    uses = {ClassGroupMapper.class, AppUserMapper.class, SchoolReportLineMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TeachingMapper {

    @Mapping(target = "evaluationsIds", source = "evaluations", qualifiedByName = "mapEvaluationsToIds")
    @Mapping(target = "classGroupId", source = "classGroup.id")
    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(target = "schoolReportLinesIds", source = "schoolReportLines", qualifiedByName = "mapSchoolReportLinesToIds")
    TeachingDTO toDto(Teaching entity);

    @Mapping(target = "evaluations", source = "evaluationsIds", qualifiedByName = "mapIdsToEvaluations")
    @Mapping(target = "classGroup", source = "classGroupId", qualifiedByName = "mapIdToClassGroup")
    @Mapping(target = "teacher", source = "teacherId", qualifiedByName = "mapIdToTeacher")
    @Mapping(target = "schoolReportLines", source = "schoolReportLinesIds", qualifiedByName = "mapIdsToSchoolReportLines")
    Teaching toEntity(TeachingDTO dto,
        @Context ClassGroupRepository classGroupRepository,
        @Context AppUserRepository appUserRepository);

    @Named("mapIdToClassGroup")
    default ClassGroup mapIdToClassGroup(Long classGroupId, @Context ClassGroupRepository classGroupRepository) {
        return classGroupRepository.getReferenceById(classGroupId);
    }

    @Named("mapIdToTeacher")
    default AppUser mapIdToTeacher(Long teacherId, @Context AppUserRepository appUserRepository) {
        return appUserRepository.getReferenceById(teacherId);
    }
    
    
    @Named("mapEvaluationsToIds")
    default List<Long> mapEvaluationsToIds(List<Evaluation> reports) {
        return reports == null ? null :
            reports.stream()
                   .map(Evaluation::getId)
                   .collect(Collectors.toList());
    }

    @Named("mapIdsToEvaluations")
    default List<Evaluation> mapIdsToSchoolReports(List<Long> ids) {
        return ids == null ? null :
            ids.stream()
               .map(id -> {
                   Evaluation eval = new Evaluation();
                   eval.setId(id);
                   return eval;
               })
               .collect(Collectors.toList());
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
            ids.stream()
               .map(id -> {
                   SchoolReportLine l = new SchoolReportLine();
                   l.setId(id);
                   return l;
               })
               .collect(Collectors.toList());
    }
}

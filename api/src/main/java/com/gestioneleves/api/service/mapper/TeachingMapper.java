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

    // @Mapping(target = "schoolReportsIds", source = "schoolReports", qualifiedByName = "mapSchoolReportsToIds")
    @Mapping(target = "classGroupId", source = "classGroup.id")
    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(target = "schoolReportLinesIds", source = "schoolReportLines", qualifiedByName = "mapSchoolReportLinesToIds")
    TeachingDTO toDto(Teaching entity);

    // @Mapping(target = "schoolReports", source = "schoolReportsIds", qualifiedByName = "mapIdsToSchoolReports")
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
    
    
    @Named("mapSchoolReportsToIds")
    default List<Long> mapSchoolReportsToIds(List<SchoolReport> reports) {
        return reports == null ? null :
            reports.stream()
                   .map(SchoolReport::getId)
                   .collect(Collectors.toList());
    }

    @Named("mapIdsToSchoolReports")
    default List<SchoolReport> mapIdsToSchoolReports(List<Long> ids) {
        return ids == null ? null :
            ids.stream()
               .map(id -> {
                   SchoolReport sr = new SchoolReport();
                   sr.setId(id);
                   return sr;
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

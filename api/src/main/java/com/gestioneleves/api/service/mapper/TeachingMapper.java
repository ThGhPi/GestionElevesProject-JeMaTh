package com.gestioneleves.api.service.mapper;

import com.gestioneleves.api.dto.TeachingDTO;
import com.gestioneleves.api.entity.*;
import org.mapstruct.*;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
    componentModel = "spring",
    uses = {ClassGroupMapper.class, AppUserMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface TeachingMapper {

    @Mapping(target = "schoolReportsIds", source = "schoolReports", qualifiedByName = "mapSchoolReportsToIds")
    @Mapping(target = "classGroup", source = "classGroup")
    @Mapping(target = "teacher", source = "teacher")
    TeachingDTO toDto(Teaching entity);

    @Mapping(target = "schoolReports", source = "schoolReportsIds", qualifiedByName = "mapIdsToSchoolReports")
    @Mapping(target = "classGroup", source = "classGroup")
    @Mapping(target = "teacher", source = "teacher")
    Teaching toEntity(TeachingDTO dto);

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
}

package com.gestioneleves.api.service.mapper;

import com.gestioneleves.api.dto.SchoolReportDTO;
import com.gestioneleves.api.entity.*;
import org.mapstruct.*;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
    componentModel = "spring",
    uses = {TeachingMapper.class, SchoolReportLineMapper.class, StudentMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SchoolReportMapper {

    @Mapping(target = "teachingsIds", source = "teachings", qualifiedByName = "mapTeachingsToIds")
    @Mapping(target = "linesIds", source = "lines", qualifiedByName = "mapLinesToIds")
    @Mapping(target = "studentDTO", source = "student")
    SchoolReportDTO toDto(SchoolReport entity);

    @Mapping(target = "teachings", source = "teachingsIds", qualifiedByName = "mapIdsToTeachings")
    @Mapping(target = "lines", source = "linesIds", qualifiedByName = "mapIdsToLines")
    @Mapping(target = "student", source = "studentDTO")
    SchoolReport toEntity(SchoolReportDTO dto);


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

    @Named("mapLinesToIds")
    default List<Long> mapLinesToIds(List<SchoolReportLine> lines) {
        return lines == null ? null :
            lines.stream()
                 .map(SchoolReportLine::getId)
                 .collect(Collectors.toList());
    }

    @Named("mapIdsToLines")
    default List<SchoolReportLine> mapIdsToLines(List<Long> ids) {
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

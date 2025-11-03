package com.gestioneleves.api.service.mapper;

import com.gestioneleves.api.dto.SchoolReportDTO;
import com.gestioneleves.api.dto.StudentDTO;
import com.gestioneleves.api.entity.*;
import com.gestioneleves.api.repository.StudentRepository;

import org.mapstruct.*;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
    componentModel = "spring",
    uses = {SchoolReportLineMapper.class, StudentMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SchoolReportMapper {

    // @Mapping(target = "teachingsIds", source = "teachings", qualifiedByName = "mapTeachingsToIds")
    @Mapping(target = "schoolReportLinesIds", source = "schoolReportLines", qualifiedByName = "mapSchoolReportLinesToIds")
    @Mapping(target = "studentDTO", source = "student")
    SchoolReportDTO toDto(SchoolReport entity);

    // @Mapping(target = "teachings", source = "teachingsIds", qualifiedByName = "mapIdsToTeachings")
    @Mapping(target = "schoolReportLines", source = "schoolReportLinesIds", qualifiedByName = "mapIdsToSchoolReportLines")
    @Mapping(target = "student", source = "studentDTO", qualifiedByName = "mapsDtoToStudent")
    SchoolReport toEntity(SchoolReportDTO dto, @Context StudentRepository studentRepository);

    @Named("mapsDtoToStudent")
    default Student mapsDtoToStudent(StudentDTO studentDTO, @Context StudentRepository studentRepository) {
        return studentRepository.getReferenceById(studentDTO.getId());
    }


    // @Named("mapTeachingsToIds")
    // default List<Long> mapTeachingsToIds(List<Teaching> teachings) {
    //     return teachings == null ? null :
    //         teachings.stream()
    //                  .map(Teaching::getId)
    //                  .collect(Collectors.toList());
    // }

    // @Named("mapIdsToTeachings")
    // default List<Teaching> mapIdsToTeachings(List<Long> ids) {
    //     return ids == null ? null :
    //         ids.stream()
    //            .map(id -> {
    //                Teaching t = new Teaching();
    //                t.setId(id);
    //                return t;
    //            })
    //            .collect(Collectors.toList());
    // }

    @Named("mapSchoolReportLinesToIds")
    default List<SchoolReportLinePK> mapSchoolReportLinesToIds(List<SchoolReportLine> lines) {
        if (lines.isEmpty()) { return null; }
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

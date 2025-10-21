package com.gestioneleves.api.dto;

import java.util.List;

import com.gestioneleves.api.entity.Student;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SchoolReportDTO {
    
    private Long id;
    private String schoolPeriod;
    private Student studentId;
    private String mention;
    private List<TeachingDTO> teachingsIds;
    private List<SchoolReportLineDTO> lines;
}

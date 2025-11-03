package com.gestioneleves.api.dto;

import java.time.LocalDate;
import java.util.List;

import com.gestioneleves.api.entity.SchoolReportLinePK;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SchoolReportDTO {
    private Long id;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private Double overallAverage;
    private String generalComment;
    private String mention;
    private StudentDTO studentDTO;
    private List<SchoolReportLinePK> schoolReportLinesIds;
}

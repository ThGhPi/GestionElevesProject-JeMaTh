package com.gestioneleves.api.dto;
import com.gestioneleves.api.entity.SchoolReportLinePK;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor 
@Builder
@Getter
@Setter
public class SchoolReportLineDTO {
    private SchoolReportLinePK id;
    private String comment;
    private TeachingDTO teachingDto;
    private SchoolReportDTO schoolReportDto;
    private Double teachingAverage;
}
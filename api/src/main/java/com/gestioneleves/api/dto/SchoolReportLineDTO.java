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
    private TeachingDTO teachingDto;
    private String comment;
    private SchoolReportDTO schoolReportDto;
    private Double teachingAverage;
}
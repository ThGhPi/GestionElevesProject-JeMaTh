package com.gestioneleves.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor 
@Builder
@Getter
@Setter
public class SchoolReportLineDTO {
    private Long id;
    private TeachingDTO teachingDto;
    private String comment;
    private SchoolReportDTO schoolReportDto;
    private Double teachingAverage;
}
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
    private TeachingDTO teachingDtoId;
    private String comment;
    private SchoolReportDTO schoolReportDtoId;
    private Double teachingAverage;
}
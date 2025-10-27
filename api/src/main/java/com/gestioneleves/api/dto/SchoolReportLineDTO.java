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
    private long teachingDtoid;
    private String comment;
    private long schoolReportDtoid;
    private Double teachingAverage;
}
package com.gestioneleves.api.dto;

import java.sql.Timestamp;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDTO {
    
    private Long id;
    private Timestamp dateAndTime;
    private Double note;
    private Double weight;
    private TeachingDTO teachingDTO;
    private StudentDTO studentDTO;}

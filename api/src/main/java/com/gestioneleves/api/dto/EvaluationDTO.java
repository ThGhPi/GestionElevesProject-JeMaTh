package com.gestioneleves.api.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EvaluationDTO {
    
    private Long id;
    private TeachingDTO teachingDTO;
    private Double note;
    private Double weight;
    private StudentDTO studentDTO;}

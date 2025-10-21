package com.gestioneleves.api.dto;

import com.gestioneleves.api.entity.Student;
import com.gestioneleves.api.entity.Teaching;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EvaluationDTO {
    
    private Long id;
    private Teaching teaching;
    private Double note;
    private Double weight;
    private Student student;}

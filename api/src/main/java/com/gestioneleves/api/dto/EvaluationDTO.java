package com.gestioneleves.api.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EvaluationDTO {
    
    private Long id;
    private long teachingDTOid;
    private Double note;
    private Double weight;
    private long studentDTOid;}

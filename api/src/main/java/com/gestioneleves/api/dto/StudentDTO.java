package com.gestioneleves.api.dto;

import java.util.List;
import java.time.LocalDate;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO { 
    private LocalDate birthday;
    private byte[] photo; 
    private List<Long> schoolReportsIds; 
    private List<Long> evaluationsIds;
    private List<Long> legalguardiansIds;
}
 
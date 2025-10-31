package com.gestioneleves.api.dto;



import java.time.LocalDate;

import com.gestioneleves.api.entity.RegistrationPK;

import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationDTO {
    private RegistrationPK id;
    private String schoolYear;
    private LocalDate registrationDate;
}

package com.gestioneleves.api.dto;



import java.time.LocalDate;

import com.gestioneleves.api.entity.RegistrationPK;

import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RegistrationDTO {
    private RegistrationPK id;
    private StudentDTO studentDTO;
    private ClassGroupDTO classGroupDTO;
    private String schoolYear;
    private LocalDate registrationDate;
}

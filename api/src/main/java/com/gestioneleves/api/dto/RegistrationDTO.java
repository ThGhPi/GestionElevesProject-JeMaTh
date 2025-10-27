package com.gestioneleves.api.dto;



import java.time.LocalDate;
import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {

    private long studentDTOid;
    private long classGroupDTOid;
    private String schoolYear;
    private LocalDate registrationDate;
}

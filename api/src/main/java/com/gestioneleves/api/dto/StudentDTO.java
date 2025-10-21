package com.gestioneleves.api.dto;

import java.util.List;
import java.time.LocalDate;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class StudentDTO extends PersonDTO {
    private LocalDate birthday;
    private byte[] photo;
    private List<SchoolReportDTO> schoolReportsIds; 
    private List<EvaluationDTO> evaluationsIds;
    private List<AppUserDTO> guardiansIds;
    private List<RegistrationDTO> registrationsIds;
}
 
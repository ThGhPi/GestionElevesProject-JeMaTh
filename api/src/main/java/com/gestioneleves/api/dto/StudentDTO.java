package com.gestioneleves.api.dto;

import java.util.List;

import com.gestioneleves.api.entity.RegistrationPK;

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
    private List<Long> schoolReportsIds; 
    private List<Long> evaluationsIds;
    private List<Long> guardiansIds;
    private List<RegistrationPK> registrationsIds;
}
 
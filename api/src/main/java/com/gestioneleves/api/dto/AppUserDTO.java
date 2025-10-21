package com.gestioneleves.api.dto;

import java.time.LocalDate;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class AppUserDTO extends PersonDTO {
    private String username;
    private String email;
    private String role;
    private LocalDate birthDate;
    private String phoneNumber;
    private String postalAdress;
}
 
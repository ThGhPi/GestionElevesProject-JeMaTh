package com.gestioneleves.api.dto;


import java.util.List;
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
    private String phoneNumber;
    private String postalAdress;
    private List<Long> teachingsIds;
    private List<Long> studentsUnderCaresIds;
} 
 
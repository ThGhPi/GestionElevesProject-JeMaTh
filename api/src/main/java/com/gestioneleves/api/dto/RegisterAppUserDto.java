package com.gestioneleves.api.dto;

import com.gestioneleves.api.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class RegisterAppUserDTO extends PersonDTO {
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
    private String postalAddress;
    private UserRole role;
}

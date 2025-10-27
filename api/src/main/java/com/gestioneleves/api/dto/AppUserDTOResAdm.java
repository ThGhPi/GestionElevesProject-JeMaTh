package com.gestioneleves.api.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDTOResAdm {
    private long id;
    private String firstname;
    private String lastname;
    private String role;
}

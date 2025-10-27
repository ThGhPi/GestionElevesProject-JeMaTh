package com.gestioneleves.api.dto;



import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDTOCreateAdmin {
    private String firstname;
    private String lastname;
    private String password;
    private String username;
    private String email;
    private String role;
    private String phone_number;
    private String postal_address;
} 
 
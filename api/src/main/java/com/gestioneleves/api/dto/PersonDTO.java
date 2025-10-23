package com.gestioneleves.api.dto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PersonDTO {
    protected Long id;
    protected String firstname;
    protected String lastname;
}

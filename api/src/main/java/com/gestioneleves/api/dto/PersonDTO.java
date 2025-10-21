package com.gestioneleves.api.dto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
public class PersonDTO {
    private Long id;
    private String firstname;
    private String lastname;
}

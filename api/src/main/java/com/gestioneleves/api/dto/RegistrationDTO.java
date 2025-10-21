package com.gestioneleves.api.dto;


import com.gestioneleves.api.entity.Student;
import java.time.LocalDate;
import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RegistrationDTO {

    private Student student;
    private ClassGroupDTO classGroup;
    private String schoolYear;
    private LocalDate registrationDate;
}

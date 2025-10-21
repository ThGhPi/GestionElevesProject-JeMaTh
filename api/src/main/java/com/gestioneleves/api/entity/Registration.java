package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "registration")
@Data
@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class Registration {

    @EmbeddedId
    private RegistrationId id;

    @ManyToOne(optional = false) 
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(optional = false) 
    @MapsId("classGroupId")
    @JoinColumn(name = "class_group_id")
    private ClassGroup classGroup;

    @Builder.Default
    @Column(nullable = false)
    private LocalDate registrationDate = LocalDate.now();

    @Column(nullable = false, length = 20)
    private String schoolYear; 
}
package com.gestioneleves.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Data
@Entity
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class Registration {

    @EmbeddedId
    @Builder.Default
    private RegistrationPK id = new RegistrationPK();

    @Column(nullable = false)
    private LocalDate registrationDate;

    @Column(nullable = false, length = 4)
    private String schoolYear;

    @ManyToOne(optional = false)
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(optional = false)
    @MapsId("classGroupId")
    @JoinColumn(name = "class_group_id")
    private ClassGroup classGroup;
}
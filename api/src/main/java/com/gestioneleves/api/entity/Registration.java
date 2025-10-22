package com.gestioneleves.api.entity;

import lombok.Data;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Data
@Entity
public class Registration {

    @EmbeddedId
    private RegistrationPK id = new RegistrationPK();

    @Column(nullable = false)
    private LocalDate registrationDate;

    @Column(nullable = false)
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
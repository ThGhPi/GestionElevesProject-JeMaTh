package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Entity
@IdClass(Registration.class)
public class Registration {

    @Id
    @Column(name = "student_id")
    private Long studentId;

    @Id
    @Column(name = "class_group_id")
    private Long classGroupId;

    @Column(nullable = false)
    private LocalDate registrationDate;

    @Column(nullable = false)
    private String schoolYear;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "class_group_id")
    private ClassGroup classGroup;
}
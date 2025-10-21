package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Student extends Person {

    private LocalDate birthday;

    @Lob
    private byte[] photo;

    @OneToMany(mappedBy = "student")
    private List<Evaluation> evaluations = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<SchoolReport> schoolReports = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    private List<Registration> registrations = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "legal_guardian_link",
            joinColumns = @JoinColumn(name = "student_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "legal_guardian_id", nullable = false)
    )
    private List<AppUser> legalGuardians;
}
package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "student")
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
@EqualsAndHashCode(callSuper = true, exclude = {"schoolReports", "evaluations", "legalguardians", "registrations"})
@ToString(callSuper = true, exclude = {"schoolReports", "evaluations", "legalguardians", "registrations"})
public class Student extends Person {

    @Column(nullable = false)
    private LocalDate birthday;

    @Lob
    private byte[] photo;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SchoolReport> schoolReports = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Evaluation> evaluations = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "legal_guardian",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "app_user_id")
    ) 
    @Builder.Default
    private List<AppUser> legalguardians = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Registration> registrations = new ArrayList<>();
}

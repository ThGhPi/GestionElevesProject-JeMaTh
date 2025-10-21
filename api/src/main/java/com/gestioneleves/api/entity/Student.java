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
@Getter 
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"schoolReports", "evaluations", "guardians", "registrations"})
@ToString(callSuper = true, exclude = {"schoolReports", "evaluations", "guardians", "registrations"})
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
    @JoinTable(name = "take_care",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "app_user_id")
    ) 
    @Builder.Default
    private List<AppUser> guardians = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Registration> registrations = new ArrayList<>();
}

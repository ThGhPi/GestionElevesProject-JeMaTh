package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@EqualsAndHashCode(exclude = {"teachings", "registrations"})
@ToString(exclude = {"teachings", "registrations"})
public class ClassGroup  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25, unique = true)
    private String name;

    @OneToMany(mappedBy = "classGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Teaching> teachings = new ArrayList<>();

    @OneToMany(mappedBy = "classGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Registration> registrations = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "head_teacher_id", nullable = false, unique = true)
    private AppUser headTeacher;
}
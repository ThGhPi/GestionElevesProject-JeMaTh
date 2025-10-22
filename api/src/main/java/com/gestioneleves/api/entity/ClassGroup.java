package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Entity
public class ClassGroup  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25, unique = true)
    private String name;

    @OneToMany(mappedBy="classGroup")
    private List<Teaching> teachings = new ArrayList<>();

    @OneToMany(mappedBy = "classGroup")
    private List<Registration> registrations = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "head_teacher_id", nullable = false, unique = true)
    private AppUser headTeacher;
}
package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class AppUser extends Person {
    private String password = "defaultValue";
    private String email;
    private String username;
    private String phoneNumber;
    private String postalAddress;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "teacher")
    private List<Teaching> teachings = new ArrayList<>();

    @OneToOne (mappedBy = "headTeacher")
    private ClassGroup classGroup;

    @ManyToMany(mappedBy = "legalGuardians")
    private List<Student> students = new ArrayList<>();
}
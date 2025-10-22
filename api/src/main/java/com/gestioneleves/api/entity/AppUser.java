package com.gestioneleves.api.entity;

import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NamedQueries({
    // @NamedQuery(name = "AppUser.findByUsername", query = "Select a from AppUser a where a.username = ?1"),
    // @NamedQuery(name = "AppUser.findByLegalGuardiansId", query = "Select s from Student s join s.legalGuardians g where g.id = ?1 "),
})
@NamedQuery(name = "AppUser.findByClassGroup", query = "Select a from AppUser a join Teaching t where t.classGroup = ?1")
public class AppUser extends Person {
    @Column(nullable = false, length = 50)
    private String password = "defaultValue";

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 50, unique = true)
    private String username;

    @Column(nullable = false, length = 50, unique = true)
    private String phoneNumber;

    @Column(nullable = false, length = 50)
    private String postalAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "teacher")
    private List<Teaching> teachings = new ArrayList<>();

    @OneToOne (mappedBy = "headTeacher")
    private ClassGroup classGroup;

    @ManyToMany(mappedBy = "legalGuardians")
    private List<Student> students = new ArrayList<>();
}
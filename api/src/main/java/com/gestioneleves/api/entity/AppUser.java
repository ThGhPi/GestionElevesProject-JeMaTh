package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(
    name = "app_user",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"username"})
    }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"studentsUnderCares", "teachings"})
@ToString(callSuper = true, exclude = {"studentsUnderCares", "teachings"})
public class AppUser extends Person {


    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(length = 20, nullable = false)
    private String phone_number;

    @Column(length = 100, nullable = false)
    private String postal_address; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole role;

    @ManyToMany(mappedBy = "legalguardians")
    private List<Student> studentsUnderCares = new ArrayList<>();

    @OneToMany(mappedBy = "teacher")
    private List<Teaching> teachings = new ArrayList<>();
}

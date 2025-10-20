package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "app_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"username"})
})
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(callSuper = true, exclude = {"studentsUnderCare", "teachings"})
@ToString(callSuper = true, exclude = {"studentsUnderCare", "teachings"})
public class AppUser extends Person {

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(length = 20)
    private String phoneNumber;

    @Column(length = 100)
    private String postalAdress; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole role;

    
    @ManyToMany(mappedBy = "guardians")
    @Builder.Default
    private List<Student> studentsUnderCare = new ArrayList<>();

    @OneToMany(mappedBy = "teacher")
    @Builder.Default
    private List<Teaching> teachings = new ArrayList<>();
}

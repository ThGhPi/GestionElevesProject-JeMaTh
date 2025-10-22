package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
@Data
@NoArgsConstructor 
@AllArgsConstructor
public abstract class Person {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstname;

    @Column(nullable = false, length = 50)
    private String lastname; 
}

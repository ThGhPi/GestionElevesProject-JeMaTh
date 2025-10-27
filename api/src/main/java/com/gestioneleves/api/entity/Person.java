package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    protected String firstname;

    @Column(nullable = true)
    protected String lastname; 
}

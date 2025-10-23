package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// import java.io.Serializable;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor 
@AllArgsConstructor
public abstract class Person /*implements Serializable*/ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, length = 50)
    protected String firstname;

    @Column(nullable = false, length = 50)
    protected String lastname;

}
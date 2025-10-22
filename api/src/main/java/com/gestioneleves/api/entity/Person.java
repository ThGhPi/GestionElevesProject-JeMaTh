package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// import java.io.Serializable;

@Data
@Getter
@Setter

@MappedSuperclass
public abstract class Person /*implements Serializable*/ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, length = 50)
    protected String firstname;

    @Column(nullable = false, length = 50)
    protected String lastname;

}
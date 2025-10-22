package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
@Entity
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Timestamp dataAndTime;

    @Column(nullable = false)
    private Double note;

    @ManyToOne(optional = false)
    @JoinColumn (name = "student_id")
    private Student student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "teaching_id")
    private Teaching teaching;
}
package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Teaching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String subjectName;

    @ManyToOne(optional = false)
    @JoinColumn(name ="class_group_id", nullable = false)
    private ClassGroup classGroup;

    @ManyToOne(optional = false)
    @JoinColumn(name ="teacher_id", nullable = false)
    private AppUser teacher;
}
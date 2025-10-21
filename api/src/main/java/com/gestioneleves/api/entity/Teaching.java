package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class Teaching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subjectName;

    @ManyToOne(optional = false)
    @JoinColumn(name ="class_group_id")
    private ClassGroup classGroup;

    @ManyToOne(optional = false)
    @JoinColumn(name ="teacher_id")
    private AppUser teacher;
}
package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Getter
@Setter
@Entity
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double weight;
    private Timestamp dataAndTime;
    private Double note;

    @ManyToOne(optional = false)
    @JoinColumn (name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "school_report_id")
    private SchoolReport schoolReport;


}
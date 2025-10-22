package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@Entity
public class SchoolReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date schoolPeriodStart;

    @Column(nullable = false)
    private Date schoolPeriodEnd;

    @Column(length = 50)
    private Double overallAverage;

    @Column(length = 20)
    private String mention;

    @Column(length = 256)
    private String generalComment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "schoolReport")
    private List<SchoolReportLine> schoolReportLines = new ArrayList<>();


}
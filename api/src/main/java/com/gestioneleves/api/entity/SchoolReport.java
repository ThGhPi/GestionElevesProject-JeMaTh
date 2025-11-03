package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
@EqualsAndHashCode(exclude = {"schoolReportLines"})
@ToString(exclude = {"schoolReportLines"})
public class SchoolReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate periodStart;

    @Column(nullable = false)
    private LocalDate periodEnd;

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
    @Builder.Default
    private List<SchoolReportLine> schoolReportLines = new ArrayList<>();


}
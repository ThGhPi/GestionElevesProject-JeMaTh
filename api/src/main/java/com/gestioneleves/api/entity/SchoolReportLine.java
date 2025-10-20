package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "school_report_line")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SchoolReportLine {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 256)
    private String comment;

    private Double teachingAverage;

    @ManyToOne(optional = false)
    @JoinColumn(name = "schoolreport_id")
    private SchoolReport schoolReport;

    @ManyToOne
    @JoinColumn(name = "teaching_id")
    private Teaching teaching;
}

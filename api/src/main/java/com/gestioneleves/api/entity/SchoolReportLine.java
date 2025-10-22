package com.gestioneleves.api.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@IdClass(SchoolReportLine.class)

public class SchoolReportLine {

    @Id
    @Column(name = "school_report_id")
    private Long schoolReportId;

    @Id
    @Column(name = "teaching_id")
    private Long teachingId;

    @Column(length = 256)
    private String comment;

    private Double teachingAverage;

    @ManyToOne(optional = false)
    @JoinColumn(name = "teaching_id")
    private Teaching teaching;

    @ManyToOne(optional = false)
    @JoinColumn(name = "school_report_id")
    private SchoolReport schoolReport;
}
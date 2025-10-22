package com.gestioneleves.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
@Entity
public class SchoolReportLine {

    @EmbeddedId
    @Builder.Default
    private SchoolReportLinePK id = new SchoolReportLinePK();

    @Column(length = 256)
    private String comment;

    private Double teachingAverage;

    @ManyToOne(optional = false)
    @MapsId("teachingId")
    @JoinColumn(name = "teaching_id")
    private Teaching teaching;

    @ManyToOne(optional = false)
    @MapsId("schoolReportId")
    @JoinColumn(name = "school_report_id")
    private SchoolReport schoolReport;
}
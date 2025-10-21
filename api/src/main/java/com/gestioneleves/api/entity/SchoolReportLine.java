package com.gestioneleves.api.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
public class SchoolReportLine {
    private String comment;
    private Double teachingAverage;

    @ManyToOne(optional = false)
    @JoinColumn(name = "teaching_id")
    private Teaching teaching;

    @ManyToOne(optional = false)
    @JoinColumn(name = "school_report_id")
    private SchoolReport schoolReport;

}
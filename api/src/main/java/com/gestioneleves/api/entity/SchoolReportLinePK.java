package com.gestioneleves.api.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class SchoolReportLinePK implements Serializable {
    
    @Column(name = "school_report_id")
    private Long schoolReportId;

    @Column(name = "teaching_id")
    private Long teachingId;

}

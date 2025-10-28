package com.gestioneleves.api.dto;

import java.util.Date;
import java.util.List;

import com.gestioneleves.api.entity.SchoolReportLinePK;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SchoolReportDTO {
    private Long id;
    private Date schoolPeriodStart;
    private Date schoolPeriodEnd;
    private StudentDTO studentDTO;
    private String mention;
    private List<Long> teachingsIds;
    private List<SchoolReportLinePK> schoolReportLinesIds;
}

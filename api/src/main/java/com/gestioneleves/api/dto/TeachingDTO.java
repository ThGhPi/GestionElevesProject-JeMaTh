package com.gestioneleves.api.dto;
import java.util.List;

import com.gestioneleves.api.entity.SchoolReportLinePK;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TeachingDTO {
    private Long id;
    private String subjectName;
    private Long classGroupId;
    private Long teacherId;
    private List<SchoolReportLinePK> schoolReportLinesIds;
}
  
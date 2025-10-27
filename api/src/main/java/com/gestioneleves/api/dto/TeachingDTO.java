package com.gestioneleves.api.dto;
import java.util.List;

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
    private ClassGroupDTO classGroup;
    private long teacherid;
    private List<Long> schoolReportsIds;
}
  
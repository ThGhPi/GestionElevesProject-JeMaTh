package com.gestioneleves.api.dto;

import java.util.List;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ClassGroupDTO {
    private Long id;
    private String name;
    private List<Long> teachingsIds;
    private List<Long> registrationsIds;
}

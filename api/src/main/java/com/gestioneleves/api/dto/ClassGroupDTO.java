package com.gestioneleves.api.dto;

import java.util.List;

import com.gestioneleves.api.entity.RegistrationPK;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassGroupDTO {
    private Long id;
    private String name;
    private List<Long> teachingsIds;
    private List<RegistrationPK> registrationsIds;
}

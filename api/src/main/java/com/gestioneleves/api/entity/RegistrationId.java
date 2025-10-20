package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RegistrationId implements Serializable {
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "class_group_id")
    private Long classGroupId;
}

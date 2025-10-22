package com.gestioneleves.api.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class RegistrationPK implements Serializable {
    
    @Column(name = "student_id")
    private Long studentId;
    
    @Column(name = "class_group_id")
    private Long classGroupId;
}

package com.gestioneleves.api.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
public class RegistrationPK implements Serializable {
    
    @Column(name = "student_id")
    private Long studentId;
    
    @Column(name = "class_group_id")
    private Long classGroupId;
}

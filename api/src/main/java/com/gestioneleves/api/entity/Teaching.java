package com.gestioneleves.api.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
@Entity
public class Teaching {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String subjectName;

    @ManyToOne(optional = false)
    @JoinColumn(name ="class_group_id", nullable = false)
    private ClassGroup classGroup;

    @ManyToOne(optional = false)
    @JoinColumn(name ="teacher_id", nullable = false)
    private AppUser teacher;

    @OneToMany(mappedBy = "teaching")
    @Builder.Default
    private List<SchoolReportLine> schoolReportLines = new ArrayList<>();
}
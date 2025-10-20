package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "school_report")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(exclude = {"teachings", "lines"})
@ToString(exclude = {"teachings", "lines"})
public class SchoolReport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "schoolperiod", nullable = false, length = 50)
    private String schoolPeriod;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToMany
    @JoinTable(name = "schoolreport_teaching",
        joinColumns = @JoinColumn(name = "schoolreport_id"),
        inverseJoinColumns = @JoinColumn(name = "teaching_id")
    )
    @Builder.Default
    private List<Teaching> teachings = new ArrayList<>();

    @OneToMany(mappedBy = "schoolReport", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SchoolReportLine> lines = new ArrayList<>();

    @Column(name = "mention", length = 50)
    private String mention;
}

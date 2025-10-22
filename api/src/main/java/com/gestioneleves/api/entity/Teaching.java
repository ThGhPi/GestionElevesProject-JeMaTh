package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "teaching")
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
@EqualsAndHashCode(exclude = {"schoolReports"})
@ToString(exclude = {"schoolReports"})
public class Teaching {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String subjectName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "class_group_id")
    private ClassGroup classGroup;

    @ManyToOne(optional = false)
    @JoinColumn(name = "teacher_id")
    private AppUser teacher;

    @ManyToMany(mappedBy = "teachings")
    @Builder.Default
    private List<SchoolReport> schoolReports = new ArrayList<>();
}

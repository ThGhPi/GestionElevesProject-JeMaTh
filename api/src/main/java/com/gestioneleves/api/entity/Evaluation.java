package com.gestioneleves.api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evaluation")
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
@Getter 
@Setter
public class Evaluation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private LocalDateTime dateAndTime;

    @Column(nullable = false)
    private Double note;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teaching_id")
    private Teaching teaching;
}

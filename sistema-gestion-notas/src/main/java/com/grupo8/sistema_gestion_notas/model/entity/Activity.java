package com.grupo8.sistema_gestion_notas.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "activity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "activity_date", nullable = false)
    private LocalDate activityDate;

    @Column(name = "percentage", nullable = false)
    private Double percentage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Course course;
}
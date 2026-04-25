package com.grupo8.sistema_gestion_notas.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "grade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "note_value", nullable = false)
    private Double noteValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;
}
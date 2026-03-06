package com.grupo8.sistema_gestion_notas.model.entity;

import com.grupo8.sistema_gestion_notas.teacher;
import com.grupo8.sistema_gestion_notas.subject;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long id;

    // Relación con Materia
    @ManyToOne
    @JoinColumn(name = "id_subject", nullable = false)
    private subject subject;

    // Relación con Profesor
    @ManyToOne
    @JoinColumn(name = "id_teacher", nullable = false)
    private teacher teacher;

    // Relación con Grupo
    @ManyToOne
    @JoinColumn(name = "id_grupo", nullable = false)
    private Group group;
}
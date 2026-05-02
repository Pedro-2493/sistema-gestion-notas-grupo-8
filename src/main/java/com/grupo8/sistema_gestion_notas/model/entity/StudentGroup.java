package com.grupo8.sistema_gestion_notas.model.entity;

import jakarta.persistence.*;
import lombok.*;
import com.grupo8.sistema_gestion_notas.model.entity.Group;

@Entity
@Table(name = "student_group")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private com.grupo8.sistema_gestion_notas.model.entity.Group group;

}
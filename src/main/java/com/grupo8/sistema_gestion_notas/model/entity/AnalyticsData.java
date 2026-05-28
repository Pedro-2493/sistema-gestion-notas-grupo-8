package com.grupo8.sistema_gestion_notas.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "analytics_data")
public class AnalyticsData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resumen_materias", columnDefinition = "TEXT")
    private String resumenMaterias;

    @Column(name = "rendimiento_periodo", columnDefinition = "TEXT")
    private String rendimientoPeriodo;

    @Column(name = "distribucion_notas", columnDefinition = "TEXT")
    private String distribucionNotas;

    @Column(name = "resumen_docentes", columnDefinition = "TEXT")
    private String resumenDocentes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

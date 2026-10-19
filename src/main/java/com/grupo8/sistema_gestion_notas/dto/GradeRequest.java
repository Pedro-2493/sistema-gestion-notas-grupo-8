package com.grupo8.sistema_gestion_notas.dto;

public record GradeRequest(
        Double value,
        String period,
        Long studentId,
        Long subjectId,
        Long teacherId
        ) {

}

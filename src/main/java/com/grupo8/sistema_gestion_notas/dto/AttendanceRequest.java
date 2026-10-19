package com.grupo8.sistema_gestion_notas.dto;

public record AttendanceRequest(
        String status,
        Long studentId,
        Long subjectId
        ) {

}

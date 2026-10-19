package com.grupo8.sistema_gestion_notas.dto;

import java.util.List;

public record SubjectRequest(
        String subjectName,
        String description,
        Long teacherId,
        List<Long> studentIds) {
}
package com.grupo8.sistema_gestion_notas.dto;

import java.time.LocalDate;

public record AttendanceDTO(
        Long id,
        LocalDate date,
        String status,
        Long studentId,
        String studentName,
        Long subjectId,
        String subjectName
        
) {}

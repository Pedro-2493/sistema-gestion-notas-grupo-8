package com.grupo8.sistema_gestion_notas.dto;

import java.time.LocalDate;

public record GradeDTO( Long id,
        Double value,
        String period,
        LocalDate registrationDate,
        Long studentId,
        String studentName,
        Long subjectId,
        String subjectName,
        Long teacherId,
        String teacherName) {

}

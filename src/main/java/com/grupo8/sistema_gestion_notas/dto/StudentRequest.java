package com.grupo8.sistema_gestion_notas.dto;

public record StudentRequest(
        String studentName,
        String email,
        String document,
        String password) {
}

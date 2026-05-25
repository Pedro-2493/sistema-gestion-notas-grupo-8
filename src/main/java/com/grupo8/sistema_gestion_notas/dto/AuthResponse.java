package com.grupo8.sistema_gestion_notas.dto;

public record AuthResponse(
    String token,
    String email,
    String rol,
    Long id,
    String nombre
) {}

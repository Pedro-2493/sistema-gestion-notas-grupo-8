package com.grupo8.sistema_gestion_notas.dto;

public record AdminRequest(
        String adminName,
        String email,
        String password
        ) {

}

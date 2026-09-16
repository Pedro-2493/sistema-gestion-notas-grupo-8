package com.grupo8.sistema_gestion_notas.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHasher {

    private final PasswordEncoder passwordEncoder;

    public PasswordHasher(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String hashearSiEsNecesario(String password) {
        if (password == null || password.startsWith("{")) {
            return password;
        }
        return passwordEncoder.encode(password);
    }
}

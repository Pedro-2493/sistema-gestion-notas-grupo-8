package com.grupo8.sistema_gestion_notas.service;

import com.grupo8.sistema_gestion_notas.model.entity.Admin;
import com.grupo8.sistema_gestion_notas.repository.AdminRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordHasher passwordHasher;

    public AdminService(AdminRepository adminRepository, PasswordHasher passwordHasher) {
        this.adminRepository = adminRepository;
        this.passwordHasher = passwordHasher;
    }

    public Admin guardar(Admin admin) {
        admin.setPassword(passwordHasher.hashearSiEsNecesario(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public List<Admin> listar() {
        return adminRepository.findAll();
    }

    public Admin buscarPorId(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado con id: " + id));
    }

    public Admin buscarPorEmail(String email) {
        return adminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado con email: " + email));
    }

    public List<Admin> buscarPorNombre(String nombre) {
        return adminRepository.findByAdminNameContainingIgnoreCase(nombre);
    }
}
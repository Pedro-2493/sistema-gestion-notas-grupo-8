package com.grupo8.sistema_gestion_notas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grupo8.sistema_gestion_notas.dto.AdminDTO;
import com.grupo8.sistema_gestion_notas.dto.AdminRequest;
import com.grupo8.sistema_gestion_notas.model.entity.Admin;
import com.grupo8.sistema_gestion_notas.repository.AdminRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordHasher passwordHasher;

    public AdminService(AdminRepository adminRepository, PasswordHasher passwordHasher) {
        this.adminRepository = adminRepository;
        this.passwordHasher = passwordHasher;
    }

    public AdminDTO crear(AdminRequest request) {
        Admin admin = new Admin();
        admin.setAdminName(request.adminName());
        admin.setEmail(request.email());
        admin.setPassword(passwordHasher.hashearSiEsNecesario(request.password()));
        return toDTO(adminRepository.save(admin));
    }

    public List<AdminDTO> listar() {
        return adminRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public AdminDTO buscarPorId(Long id) {
        return toDTO(buscarEntidadPorId(id));
    }

    public AdminDTO buscarPorEmail(String email) {
        return adminRepository.findByEmail(email)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado con email: " + email));
    }

    public List<AdminDTO> buscarPorNombre(String nombre) {
        return adminRepository.findByAdminNameContainingIgnoreCase(nombre).stream()
                .map(this::toDTO)
                .toList();
    }

    private Admin buscarEntidadPorId(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado con id: " + id));
    }

    private AdminDTO toDTO(Admin a) {
        return new AdminDTO(a.getId(), a.getAdminName(), a.getEmail());
    }
}
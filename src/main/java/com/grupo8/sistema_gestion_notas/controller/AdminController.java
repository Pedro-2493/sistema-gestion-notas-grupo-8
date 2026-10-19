package com.grupo8.sistema_gestion_notas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo8.sistema_gestion_notas.dto.AdminDTO;
import com.grupo8.sistema_gestion_notas.dto.AdminRequest;
import com.grupo8.sistema_gestion_notas.service.AdminService;

@RestController
@RequestMapping("/api/admins")
@PreAuthorize("hasRole('ADMINISTRADOR')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<AdminDTO> crear(@RequestBody AdminRequest request) {
        return ResponseEntity.ok(adminService.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>> listar() {
        return ResponseEntity.ok(adminService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AdminDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(adminService.buscarPorNombre(nombre));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AdminDTO> buscarPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(adminService.buscarPorEmail(email));
    }
}
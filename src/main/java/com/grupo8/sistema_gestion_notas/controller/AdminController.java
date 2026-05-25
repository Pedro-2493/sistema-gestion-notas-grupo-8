package com.grupo8.sistema_gestion_notas.controller;

import com.grupo8.sistema_gestion_notas.model.entity.Admin;
import com.grupo8.sistema_gestion_notas.service.AdminService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(AdminService adminService, PasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<Admin> crear(@RequestBody Admin admin) {
        if (admin.getPassword() != null && !admin.getPassword().startsWith("{")) {
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        }
        return ResponseEntity.ok(adminService.guardar(admin));
    }

    @GetMapping
    public ResponseEntity<List<Admin>> listar() {
        return ResponseEntity.ok(adminService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Admin>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(adminService.buscarPorNombre(nombre));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Admin> buscarPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(adminService.buscarPorEmail(email));
    }
}

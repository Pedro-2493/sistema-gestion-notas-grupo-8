package com.grupo8.sistema_gestion_notas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo8.sistema_gestion_notas.model.entity.Admin;
import com.grupo8.sistema_gestion_notas.service.AdminService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;
    

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

  @PostMapping
@PreAuthorize("hasRole('ADMINISTRADOR')")
public ResponseEntity<Admin> crear(@RequestBody Admin admin) {
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

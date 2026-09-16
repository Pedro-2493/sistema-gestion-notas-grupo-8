package com.grupo8.sistema_gestion_notas.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo8.sistema_gestion_notas.model.entity.Teacher;
import com.grupo8.sistema_gestion_notas.service.TeacherService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;


    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
        
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Teacher> crear(@RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherService.guardar(teacher));
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> listar() {
        return ResponseEntity.ok(teacherService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Teacher>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(teacherService.buscarPorNombre(nombre));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Teacher> actualizar(@PathVariable Long id, @RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherService.actualizar(id, teacher));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        teacherService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

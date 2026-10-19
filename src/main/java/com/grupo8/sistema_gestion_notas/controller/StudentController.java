package com.grupo8.sistema_gestion_notas.controller;

import com.grupo8.sistema_gestion_notas.service.StudentService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.grupo8.sistema_gestion_notas.dto.StudentDTO;
import com.grupo8.sistema_gestion_notas.dto.StudentRequest;
import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<StudentDTO> crear(@RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentService.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> listar() {
        return ResponseEntity.ok(studentService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<StudentDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(studentService.buscarPorNombre(nombre));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<StudentDTO> actualizar(@PathVariable Long id, @RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        studentService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
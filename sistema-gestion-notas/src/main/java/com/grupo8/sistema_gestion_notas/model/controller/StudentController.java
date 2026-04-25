package com.grupo8.sistema_gestion_notas.model.controller;


import com.grupo8.sistema_gestion_notas.model.entity.Student;
import com.grupo8.sistema_gestion_notas.model.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> crear(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.guardar(student));
    }

    @GetMapping
    public ResponseEntity<List<Student>> listar() {
        return ResponseEntity.ok(studentService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Student>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(studentService.buscarPorNombre(nombre));
    }
}
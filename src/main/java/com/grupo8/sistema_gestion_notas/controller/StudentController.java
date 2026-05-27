package com.grupo8.sistema_gestion_notas.controller;

import com.grupo8.sistema_gestion_notas.model.entity.Student;
import com.grupo8.sistema_gestion_notas.service.StudentService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;

    public StudentController(StudentService studentService, PasswordEncoder passwordEncoder) {
        this.studentService = studentService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<Student> crear(@RequestBody Student student) {
        if (student.getPassword() != null && !student.getPassword().startsWith("{")) {
            student.setPassword(passwordEncoder.encode(student.getPassword()));
        }
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

    @PutMapping("/{id}")
    public ResponseEntity<Student> actualizar(@PathVariable Long id, @RequestBody Student student) {
        if (student.getPassword() != null && !student.getPassword().startsWith("{") && !student.getPassword().isEmpty()) {
            student.setPassword(passwordEncoder.encode(student.getPassword()));
        }
        return ResponseEntity.ok(studentService.actualizar(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        studentService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

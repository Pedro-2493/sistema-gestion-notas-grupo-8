package com.grupo8.sistema_gestion_notas.controller;

import com.grupo8.sistema_gestion_notas.model.entity.Teacher;
import com.grupo8.sistema_gestion_notas.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final PasswordEncoder passwordEncoder;

    public TeacherController(TeacherService teacherService, PasswordEncoder passwordEncoder) {
        this.teacherService = teacherService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<Teacher> crear(@RequestBody Teacher teacher) {
        if (teacher.getPassword() != null && !teacher.getPassword().startsWith("{")) {
            teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        }
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

    @PutMapping("/{id}/avatar")
    public ResponseEntity<Teacher> actualizarAvatar(@PathVariable Long id, @RequestBody String avatar) {
        return ResponseEntity.ok(teacherService.actualizarAvatar(id, avatar));
    }
}

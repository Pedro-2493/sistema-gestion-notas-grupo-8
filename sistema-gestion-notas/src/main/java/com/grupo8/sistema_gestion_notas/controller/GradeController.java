package com.grupo8.sistema_gestion_notas.controller;



import com.grupo8.sistema_gestion_notas.model.entity.Grade;
import com.grupo8.sistema_gestion_notas.service.GradeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    public ResponseEntity<Grade> crear(@RequestBody Grade grade) {
        return ResponseEntity.ok(gradeService.guardar(grade));
    }

    @GetMapping
    public ResponseEntity<List<Grade>> listar() {
        return ResponseEntity.ok(gradeService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grade> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(gradeService.buscarPorId(id));
    }

    @GetMapping("/estudiante/{studentId}")
    public ResponseEntity<List<Grade>> porEstudiante(@PathVariable Long studentId) {
        return ResponseEntity.ok(gradeService.buscarPorEstudiante(studentId));
    }

    @GetMapping("/estudiante/{studentId}/periodo")
    public ResponseEntity<List<Grade>> porEstudianteYPeriodo(
            @PathVariable Long studentId,
            @RequestParam String period) {
        return ResponseEntity.ok(gradeService.buscarPorEstudianteYPeriodo(studentId, period));
    }
}
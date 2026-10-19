package com.grupo8.sistema_gestion_notas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo8.sistema_gestion_notas.dto.GradeDTO;
import com.grupo8.sistema_gestion_notas.dto.GradeRequest;
import com.grupo8.sistema_gestion_notas.service.GradeService;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMINISTRADOR')")
    public ResponseEntity<GradeDTO> crear(@RequestBody GradeRequest request) {
        return ResponseEntity.ok(gradeService.crear(request));
    }

    @GetMapping
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<GradeDTO>> listar() {
        return ResponseEntity.ok(gradeService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(gradeService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMINISTRADOR')")
    public ResponseEntity<GradeDTO> actualizar(@PathVariable Long id, @RequestBody GradeRequest request) {
        return ResponseEntity.ok(gradeService.actualizar(id, request));
    }

    @GetMapping("/estudiante/{studentId}")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMINISTRADOR') or "
            + "(hasRole('ESTUDIANTE') and #studentId == authentication.principal.id())")
    public ResponseEntity<List<GradeDTO>> porEstudiante(@PathVariable Long studentId) {
        return ResponseEntity.ok(gradeService.buscarPorEstudiante(studentId));
    }

    @GetMapping("/estudiante/{studentId}/periodo")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMINISTRADOR') or "
            + "(hasRole('ESTUDIANTE') and #studentId == authentication.principal.id())")
    public ResponseEntity<List<GradeDTO>> porEstudianteYPeriodo(
            @PathVariable Long studentId,
            @RequestParam String period) {
        return ResponseEntity.ok(gradeService.buscarPorEstudianteYPeriodo(studentId, period));
    }
}

package com.grupo8.sistema_gestion_notas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupo8.sistema_gestion_notas.dto.SubjectDTO;
import com.grupo8.sistema_gestion_notas.dto.SubjectRequest;
import com.grupo8.sistema_gestion_notas.service.SubjectService;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<SubjectDTO> crear(@RequestBody SubjectRequest request) {
        return ResponseEntity.ok(subjectService.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> listar() {
        return ResponseEntity.ok(subjectService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(subjectService.buscarPorId(id));
    }

    @GetMapping("/docente/{teacherId}")
    public ResponseEntity<List<SubjectDTO>> buscarPorDocente(@PathVariable Long teacherId) {
        return ResponseEntity.ok(subjectService.buscarPorDocente(teacherId));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<SubjectDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(subjectService.buscarPorNombre(nombre));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<SubjectDTO> actualizar(@PathVariable Long id, @RequestBody SubjectRequest request) {
        return ResponseEntity.ok(subjectService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        subjectService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
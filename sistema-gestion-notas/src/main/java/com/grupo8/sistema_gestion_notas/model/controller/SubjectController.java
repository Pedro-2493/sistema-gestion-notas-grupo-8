package com.grupo8.sistema_gestion_notas.model.controller;



import com.grupo8.sistema_gestion_notas.model.entity.Subject;
import com.grupo8.sistema_gestion_notas.model.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<Subject> crear(@RequestBody Subject subject) {
        return ResponseEntity.ok(subjectService.guardar(subject));
    }

    @GetMapping
    public ResponseEntity<List<Subject>> listar() {
        return ResponseEntity.ok(subjectService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(subjectService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Subject>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(subjectService.buscarPorNombre(nombre));
    }
}
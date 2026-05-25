package com.grupo8.sistema_gestion_notas.controller;



import com.grupo8.sistema_gestion_notas.model.entity.Subject;
import com.grupo8.sistema_gestion_notas.service.SubjectService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") 
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

    @PutMapping("/{id}")
    public ResponseEntity<Subject> actualizar(@PathVariable Long id, @RequestBody Subject subject) {
        return ResponseEntity.ok(subjectService.actualizar(id, subject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        subjectService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
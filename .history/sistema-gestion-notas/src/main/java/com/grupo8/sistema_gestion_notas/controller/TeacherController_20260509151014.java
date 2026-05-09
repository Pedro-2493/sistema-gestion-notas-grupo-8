package com.grupo8.sistema_gestion_notas.controller;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
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
}
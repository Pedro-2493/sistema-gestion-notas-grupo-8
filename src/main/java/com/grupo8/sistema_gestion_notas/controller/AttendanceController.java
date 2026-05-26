package com.grupo8.sistema_gestion_notas.controller;



import com.grupo8.sistema_gestion_notas.model.entity.Attendance;
import com.grupo8.sistema_gestion_notas.service.AttendanceService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public ResponseEntity<Attendance> crear(@RequestBody Attendance attendance) {
        return ResponseEntity.ok(attendanceService.guardar(attendance));
    }

    @GetMapping
    public ResponseEntity<List<Attendance>> listar() {
        return ResponseEntity.ok(attendanceService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendance> actualizar(@PathVariable Long id, @RequestBody Attendance attendance) {
        return ResponseEntity.ok(attendanceService.actualizar(id, attendance));
    }

    @GetMapping("/estudiante/{studentId}")
    public ResponseEntity<List<Attendance>> porEstudiante(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.buscarPorEstudiante(studentId));
    }

    @GetMapping("/estudiante/{studentId}/materia/{subjectId}")
    public ResponseEntity<List<Attendance>> porEstudianteYMateria(
            @PathVariable Long studentId,
            @PathVariable Long subjectId) {
        return ResponseEntity.ok(attendanceService.buscarPorEstudianteYMateria(studentId, subjectId));
    }
}
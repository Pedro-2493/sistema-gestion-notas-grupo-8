package com.grupo8.sistema_gestion_notas.model.controller;



import com.grupo8.sistema_gestion_notas.model.entity.Attendance;
import com.grupo8.sistema_gestion_notas.model.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
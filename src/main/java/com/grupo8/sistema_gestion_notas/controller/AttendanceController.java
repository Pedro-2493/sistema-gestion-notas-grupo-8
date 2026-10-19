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
import org.springframework.web.bind.annotation.RestController;

import com.grupo8.sistema_gestion_notas.dto.AttendanceDTO;
import com.grupo8.sistema_gestion_notas.dto.AttendanceRequest;
import com.grupo8.sistema_gestion_notas.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<AttendanceDTO> crear(@RequestBody AttendanceRequest request) {
        return ResponseEntity.ok(attendanceService.crear(request));
    }

    @GetMapping
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<AttendanceDTO>> listar() {
        return ResponseEntity.ok(attendanceService.listar());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<AttendanceDTO> actualizar(@PathVariable Long id, @RequestBody AttendanceRequest request) {
        return ResponseEntity.ok(attendanceService.actualizar(id, request));
    }

    @GetMapping("/estudiante/{studentId}")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMINISTRADOR') or "
            + "(hasRole('ESTUDIANTE') and #studentId == authentication.principal.id())")
    public ResponseEntity<List<AttendanceDTO>> porEstudiante(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.buscarPorEstudiante(studentId));
    }

    @GetMapping("/estudiante/{studentId}/materia/{subjectId}")
    @PreAuthorize("hasRole('TEACHER') or hasRole('ADMINISTRADOR') or "
            + "(hasRole('ESTUDIANTE') and #studentId == authentication.principal.id())")
    public ResponseEntity<List<AttendanceDTO>> porEstudianteYMateria(
            @PathVariable Long studentId,
            @PathVariable Long subjectId) {
        return ResponseEntity.ok(attendanceService.buscarPorEstudianteYMateria(studentId, subjectId));
    }
}

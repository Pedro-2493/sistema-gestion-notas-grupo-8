package com.grupo8.sistema_gestion_notas.service;



import com.grupo8.sistema_gestion_notas.model.entity.Attendance;
import com.grupo8.sistema_gestion_notas.repository.AttendanceRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public Attendance guardar(Attendance attendance) {
        attendance.setDate(LocalDate.now());
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> listar() {
        return attendanceRepository.findAll();
    }

    public List<Attendance> buscarPorEstudiante(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    public List<Attendance> buscarPorEstudianteYMateria(Long studentId, Long subjectId) {
        return attendanceRepository.findByStudentIdAndSubjectId(studentId, subjectId);
    }
}
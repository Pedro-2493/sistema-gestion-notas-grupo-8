package com.grupo8.sistema_gestion_notas.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.grupo8.sistema_gestion_notas.dto.AttendanceDTO;
import com.grupo8.sistema_gestion_notas.dto.AttendanceRequest;
import com.grupo8.sistema_gestion_notas.model.entity.Attendance;
import com.grupo8.sistema_gestion_notas.model.entity.Student;
import com.grupo8.sistema_gestion_notas.model.entity.Subject;
import com.grupo8.sistema_gestion_notas.repository.AttendanceRepository;
import com.grupo8.sistema_gestion_notas.repository.StudentRepository;
import com.grupo8.sistema_gestion_notas.repository.SubjectRepository;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, StudentRepository studentRepository,
            SubjectRepository subjectRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public AttendanceDTO crear(AttendanceRequest request) {
        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado: " + request.studentId()));
        Subject subject = subjectRepository.findById(request.subjectId())
                .orElseThrow(() -> new RuntimeException("Materia no encontrada: " + request.subjectId()));

        Attendance attendance = new Attendance();
        attendance.setStatus(request.status());
        attendance.setDate(LocalDate.now());
        attendance.setStudent(student);
        attendance.setSubject(subject);

        return toDTO(attendanceRepository.save(attendance));
    }

    public List<AttendanceDTO> listar() {
        return attendanceRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public List<AttendanceDTO> buscarPorEstudiante(Long studentId) {
        return attendanceRepository.findByStudentId(studentId).stream()
                .map(this::toDTO)
                .toList();
    }

    public List<AttendanceDTO> buscarPorEstudianteYMateria(Long studentId, Long subjectId) {
        return attendanceRepository.findByStudentIdAndSubjectId(studentId, subjectId).stream()
                .map(this::toDTO)
                .toList();
    }

    public AttendanceDTO actualizar(Long id, AttendanceRequest datos) {
        Attendance existing = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistencia no encontrada"));
        if (datos.status() != null) {
            existing.setStatus(datos.status());
        }
        return toDTO(attendanceRepository.save(existing));
    }

    private AttendanceDTO toDTO(Attendance a) {
        return new AttendanceDTO(
                a.getId(),
                a.getDate(),
                a.getStatus(),
                a.getStudent().getId(),
                a.getStudent().getStudentName(),
                a.getSubject().getId(),
                a.getSubject().getSubjectName());
    }
}
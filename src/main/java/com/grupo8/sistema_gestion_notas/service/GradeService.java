package com.grupo8.sistema_gestion_notas.service;

import com.grupo8.sistema_gestion_notas.dto.GradeDTO;
import com.grupo8.sistema_gestion_notas.dto.GradeRequest;
import com.grupo8.sistema_gestion_notas.model.entity.Grade;
import com.grupo8.sistema_gestion_notas.model.entity.Student;
import com.grupo8.sistema_gestion_notas.model.entity.Subject;
import com.grupo8.sistema_gestion_notas.model.entity.Teacher;
import com.grupo8.sistema_gestion_notas.repository.GradeRepository;
import com.grupo8.sistema_gestion_notas.repository.StudentRepository;
import com.grupo8.sistema_gestion_notas.repository.SubjectRepository;
import com.grupo8.sistema_gestion_notas.repository.TeacherRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    public GradeService(GradeRepository gradeRepository, StudentRepository studentRepository,
            SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    public GradeDTO crear(GradeRequest request) {
        Student student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado: " + request.studentId()));
        Subject subject = subjectRepository.findById(request.subjectId())
                .orElseThrow(() -> new RuntimeException("Materia no encontrada: " + request.subjectId()));
        Teacher teacher = teacherRepository.findById(request.teacherId())
                .orElseThrow(() -> new RuntimeException("Teacher no encontrado: " + request.teacherId()));

        Grade grade = new Grade();
        grade.setValue(request.value());
        grade.setPeriod(request.period());
        grade.setRegistrationDate(LocalDate.now());
        grade.setStudent(student);
        grade.setSubject(subject);
        grade.setTeacher(teacher);

        return toDTO(gradeRepository.save(grade));
    }

    public List<GradeDTO> listar() {
        return gradeRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public GradeDTO buscarPorId(Long id) {
        return toDTO(buscarEntidadPorId(id));
    }

    public List<GradeDTO> buscarPorEstudiante(Long studentId) {
        return gradeRepository.findByStudentId(studentId).stream()
                .map(this::toDTO)
                .toList();
    }

    public List<GradeDTO> buscarPorEstudianteYPeriodo(Long studentId, String period) {
        return gradeRepository.findByStudentIdAndPeriod(studentId, period).stream()
                .map(this::toDTO)
                .toList();
    }

    public GradeDTO actualizar(Long id, GradeRequest datos) {
        Grade existing = buscarEntidadPorId(id);
        if (datos.value() != null) {
            existing.setValue(datos.value());
        }
        if (datos.period() != null) {
            existing.setPeriod(datos.period());
        }
        return toDTO(gradeRepository.save(existing));
    }

    private Grade buscarEntidadPorId(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada"));
    }

    private GradeDTO toDTO(Grade g) {
        return new GradeDTO(
                g.getId(),
                g.getValue(),
                g.getPeriod(),
                g.getRegistrationDate(),
                g.getStudent().getId(),
                g.getStudent().getStudentName(),
                g.getSubject().getId(),
                g.getSubject().getSubjectName(),
                g.getTeacher().getId(),
                g.getTeacher().getTeacherName());
    }
}
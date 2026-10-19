package com.grupo8.sistema_gestion_notas.service;

import com.grupo8.sistema_gestion_notas.dto.SubjectDTO;
import com.grupo8.sistema_gestion_notas.dto.SubjectRequest;
import com.grupo8.sistema_gestion_notas.model.entity.Student;
import com.grupo8.sistema_gestion_notas.model.entity.Subject;
import com.grupo8.sistema_gestion_notas.model.entity.Teacher;
import com.grupo8.sistema_gestion_notas.repository.StudentRepository;
import com.grupo8.sistema_gestion_notas.repository.SubjectRepository;
import com.grupo8.sistema_gestion_notas.repository.TeacherRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public SubjectService(SubjectRepository subjectRepository, StudentRepository studentRepository,
            TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public SubjectDTO crear(SubjectRequest request) {
        Subject subject = new Subject();
        subject.setSubjectName(request.subjectName());
        subject.setDescription(request.description());
        subject.setTeacherId(request.teacherId());
        subject.setStudents(resolverEstudiantes(request.studentIds()));
        return toDTO(subjectRepository.save(subject));
    }

    public List<SubjectDTO> listar() {
        return subjectRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public SubjectDTO buscarPorId(Long id) {
        return toDTO(buscarEntidadPorId(id));
    }

    public List<SubjectDTO> buscarPorNombre(String nombre) {
        return subjectRepository.findBySubjectNameContainingIgnoreCase(nombre).stream()
                .map(this::toDTO)
                .toList();
    }

    public List<SubjectDTO> buscarPorDocente(Long teacherId) {
        return subjectRepository.findByTeacherId(teacherId).stream()
                .map(this::toDTO)
                .toList();
    }

    public SubjectDTO actualizar(Long id, SubjectRequest datos) {
        Subject existente = buscarEntidadPorId(id);
        existente.setSubjectName(datos.subjectName());
        existente.setDescription(datos.description());
        existente.setTeacherId(datos.teacherId());
        if (datos.studentIds() != null) {
            existente.setStudents(resolverEstudiantes(datos.studentIds()));
        }
        return toDTO(subjectRepository.save(existente));
    }

    public void eliminar(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Materia no encontrada");
        }
        subjectRepository.deleteById(id);
    }

    private Subject buscarEntidadPorId(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
    }

    private List<Student> resolverEstudiantes(List<Long> studentIds) {
        if (studentIds == null) {
            return List.of();
        }
        return studentIds.stream()
                .map(idEst -> studentRepository.findById(idEst)
                        .orElseThrow(() -> new RuntimeException("Estudiante no encontrado: " + idEst)))
                .toList();
    }

    private SubjectDTO toDTO(Subject s) {
        String teacherName = teacherRepository.findById(s.getTeacherId())
                .map(Teacher::getTeacherName)
                .orElse(null);
        int studentCount = s.getStudents() != null ? s.getStudents().size() : 0;
        return new SubjectDTO(s.getId(), s.getSubjectName(), s.getDescription(),
                s.getTeacherId(), teacherName, studentCount);
    }
}
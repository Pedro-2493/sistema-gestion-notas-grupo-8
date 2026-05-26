package com.grupo8.sistema_gestion_notas.service;

import com.grupo8.sistema_gestion_notas.model.entity.Student;
import com.grupo8.sistema_gestion_notas.model.entity.Subject;
import com.grupo8.sistema_gestion_notas.repository.StudentRepository;
import com.grupo8.sistema_gestion_notas.repository.SubjectRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    public SubjectService(SubjectRepository subjectRepository, StudentRepository studentRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    public Subject guardar(Subject subject) {
        if (subject.getStudents() != null) {
            List<Student> managed = subject.getStudents().stream()
                .map(s -> studentRepository.findById(s.getId())
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado: " + s.getId())))
                .toList();
            subject.setStudents(managed);
        }
        return subjectRepository.save(subject);
    }

    public List<Subject> listar() {
        return subjectRepository.findAll();
    }

    public Subject buscarPorId(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
    }

    public List<Subject> buscarPorNombre(String nombre) {
        return subjectRepository.findBySubjectNameContainingIgnoreCase(nombre);
    }

    public List<Subject> buscarPorDocente(Long teacherId) {
        return subjectRepository.findByTeacherId(teacherId);
    }

    public Subject actualizar(Long id, Subject datos) {
        Subject existente = buscarPorId(id);
        existente.setSubjectName(datos.getSubjectName());
        existente.setDescription(datos.getDescription());
        existente.setTeacherId(datos.getTeacherId());
        if (datos.getStudents() != null) {
            List<Student> managed = datos.getStudents().stream()
                .map(s -> studentRepository.findById(s.getId())
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado: " + s.getId())))
                .toList();
            existente.setStudents(managed);
        }
        return subjectRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Materia no encontrada");
        }
        subjectRepository.deleteById(id);
    }
}
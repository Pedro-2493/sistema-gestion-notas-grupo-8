package com.grupo8.sistema_gestion_notas.service;

import com.grupo8.sistema_gestion_notas.dto.StudentDTO;
import com.grupo8.sistema_gestion_notas.dto.StudentRequest;
import com.grupo8.sistema_gestion_notas.model.entity.Student;
import com.grupo8.sistema_gestion_notas.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordHasher passwordHasher;

    public StudentService(StudentRepository studentRepository, PasswordHasher passwordHasher) {
        this.studentRepository = studentRepository;
        this.passwordHasher = passwordHasher;
    }

    public StudentDTO crear(StudentRequest request) {
        Student student = new Student();
        student.setStudentName(request.studentName());
        student.setEmail(request.email());
        student.setDocument(request.document());
        student.setPassword(passwordHasher.hashearSiEsNecesario(request.password()));
        Student guardado = studentRepository.save(student);
        return toDTO(guardado);
    }

    public List<StudentDTO> listar() {
        return studentRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public StudentDTO buscarPorId(Long id) {
        return toDTO(buscarEntidadPorId(id));
    }

    public List<StudentDTO> buscarPorNombre(String nombre) {
        return studentRepository.findByStudentNameContainingIgnoreCase(nombre).stream()
                .map(this::toDTO)
                .toList();
    }

    public StudentDTO actualizar(Long id, StudentRequest datos) {
        Student existente = buscarEntidadPorId(id);
        existente.setStudentName(datos.studentName());
        existente.setEmail(datos.email());
        existente.setDocument(datos.document());
        if (datos.password() != null && !datos.password().isEmpty()) {
            existente.setPassword(passwordHasher.hashearSiEsNecesario(datos.password()));
        }
        return toDTO(studentRepository.save(existente));
    }

    public void eliminar(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Estudiante no encontrado");
        }
        studentRepository.deleteById(id);
    }

    private Student buscarEntidadPorId(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    private StudentDTO toDTO(Student s) {
        return new StudentDTO(s.getId(), s.getStudentName(), s.getEmail(), s.getDocument());
    }
}
package com.grupo8.sistema_gestion_notas.service;

import com.grupo8.sistema_gestion_notas.model.entity.Student;
import com.grupo8.sistema_gestion_notas.repository.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student guardar(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> listar() {
        return studentRepository.findAll();
    }

    public Student buscarPorId(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    public List<Student> buscarPorNombre(String nombre) {
        return studentRepository.findByStudentNameContainingIgnoreCase(nombre);
    }
}
package com.grupo8.sistema_gestion_notas.service;

import com.grupo8.sistema_gestion_notas.model.entity.Teacher;
import com.grupo8.sistema_gestion_notas.repository.TeacherRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher guardar(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<Teacher> listar() {
        return teacherRepository.findAll();
    }

    public Teacher buscarPorId(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
    }
}

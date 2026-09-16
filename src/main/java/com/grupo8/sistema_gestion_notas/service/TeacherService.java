package com.grupo8.sistema_gestion_notas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grupo8.sistema_gestion_notas.model.entity.Teacher;
import com.grupo8.sistema_gestion_notas.repository.TeacherRepository;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final PasswordHasher passwordHasher;

    public TeacherService(TeacherRepository teacherRepository, PasswordHasher passwordHasher) {
        this.teacherRepository = teacherRepository;
        this.passwordHasher = passwordHasher;
    }

    public Teacher guardar(Teacher teacher) {
        teacher.setPassword(passwordHasher.hashearSiEsNecesario(teacher.getPassword()));
        return teacherRepository.save(teacher);
    }

    public List<Teacher> listar() {
        return teacherRepository.findAll();
    }

    public Teacher buscarPorId(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher no encontrado con id: " + id));
    }

    public List<Teacher> buscarPorNombre(String nombre) {
        return teacherRepository.findByTeacherNameContainingIgnoreCase(nombre);
    }

    public Teacher actualizar(Long id, Teacher datos) {
        Teacher existente = buscarPorId(id);
        if (datos.getTeacherName() != null) {
            existente.setTeacherName(datos.getTeacherName());
        }
        if (datos.getEmail() != null) {
            existente.setEmail(datos.getEmail());
        }
        if (datos.getPassword() != null && !datos.getPassword().isBlank()) {
            existente.setPassword(passwordHasher.hashearSiEsNecesario(datos.getPassword()));
        }
        return teacherRepository.save(existente);
    }

    public void eliminar(Long id) {
        teacherRepository.deleteById(id);
    }
}
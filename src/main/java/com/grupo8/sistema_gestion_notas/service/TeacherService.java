package com.grupo8.sistema_gestion_notas.service;

import com.grupo8.sistema_gestion_notas.model.entity.Teacher;
import com.grupo8.sistema_gestion_notas.repository.TeacherRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    public TeacherService(TeacherRepository teacherRepository, PasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Teacher guardar(Teacher teacher) {
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
        if (datos.getPassword() != null && !datos.getPassword().isBlank() && !datos.getPassword().startsWith("{")) {
            existente.setPassword(passwordEncoder.encode(datos.getPassword()));
        }
        return teacherRepository.save(existente);
    }

    public void eliminar(Long id) {
        teacherRepository.deleteById(id);
    }
}

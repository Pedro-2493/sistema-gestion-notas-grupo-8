package com.grupo8.sistema_gestion_notas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grupo8.sistema_gestion_notas.dto.TeacherDTO;
import com.grupo8.sistema_gestion_notas.dto.TeacherRequest;
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

    public TeacherDTO crear(TeacherRequest request) {
        Teacher teacher = new Teacher();
        teacher.setTeacherName(request.teacherName());
        teacher.setEmail(request.email());
        teacher.setPassword(passwordHasher.hashearSiEsNecesario(request.password()));
        return toDTO(teacherRepository.save(teacher));
    }

    public List<TeacherDTO> listar() {
        return teacherRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public TeacherDTO buscarPorId(Long id) {
        return toDTO(buscarEntidadPorId(id));
    }

    public List<TeacherDTO> buscarPorNombre(String nombre) {
        return teacherRepository.findByTeacherNameContainingIgnoreCase(nombre).stream()
                .map(this::toDTO)
                .toList();
    }

    public TeacherDTO actualizar(Long id, TeacherRequest datos) {
        Teacher existente = buscarEntidadPorId(id);
        if (datos.teacherName() != null) {
            existente.setTeacherName(datos.teacherName());
        }
        if (datos.email() != null) {
            existente.setEmail(datos.email());
        }
        if (datos.password() != null && !datos.password().isBlank()) {
            existente.setPassword(passwordHasher.hashearSiEsNecesario(datos.password()));
        }
        return toDTO(teacherRepository.save(existente));
    }

    public void eliminar(Long id) {
        teacherRepository.deleteById(id);
    }

    private Teacher buscarEntidadPorId(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher no encontrado con id: " + id));
    }

    private TeacherDTO toDTO(Teacher t) {
        return new TeacherDTO(t.getId(), t.getTeacherName(), t.getEmail());
    }
}
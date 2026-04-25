package com.grupo8.sistema_gestion_notas.model.service;

import com.grupo8.sistema_gestion_notas.model.entity.Subject;
import com.grupo8.sistema_gestion_notas.model.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject guardar(Subject subject) {
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
}
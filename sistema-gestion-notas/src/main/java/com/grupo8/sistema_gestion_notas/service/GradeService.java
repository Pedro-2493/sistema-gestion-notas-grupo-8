package com.grupo8.sistema_gestion_notas.service;


import com.grupo8.sistema_gestion_notas.model.entity.Grade;
import com.grupo8.sistema_gestion_notas.repository.GradeRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public Grade guardar(Grade grade) {
        grade.setRegistrationDate(LocalDate.now());
        return gradeRepository.save(grade);
    }

    public List<Grade> listar() {
        return gradeRepository.findAll();
    }

    public Grade buscarPorId(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota no encontrada"));
    }

    public List<Grade> buscarPorEstudiante(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    public List<Grade> buscarPorEstudianteYPeriodo(Long studentId, String period) {
        return gradeRepository.findByStudentIdAndPeriod(studentId, period);
    }
}
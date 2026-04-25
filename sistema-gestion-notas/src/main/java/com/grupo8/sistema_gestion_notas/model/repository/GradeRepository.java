package com.grupo8.sistema_gestion_notas.model.repository;

import com.grupo8.sistema_gestion_notas.model.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentId(Long studentId);
    List<Grade> findBySubjectId(Long subjectId);
    List<Grade> findByStudentIdAndPeriod(Long studentId, String period);
}
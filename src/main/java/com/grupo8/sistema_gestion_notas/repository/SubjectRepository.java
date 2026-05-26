package com.grupo8.sistema_gestion_notas.repository;

import com.grupo8.sistema_gestion_notas.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findBySubjectNameContainingIgnoreCase(String subjectName);
    List<Subject> findByTeacherId(Long teacherId);
}
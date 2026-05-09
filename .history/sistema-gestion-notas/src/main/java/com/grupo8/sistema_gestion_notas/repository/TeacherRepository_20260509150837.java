package com.grupo8.sistema_gestion_notas.repository;

import com.grupo8.sistema_gestion_notas.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByTeacherNameContainingIgnoreCase(String nombre);
}
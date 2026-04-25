package com.grupo8.sistema_gestion_notas.model.repository;

import com.grupo8.sistema_gestion_notas.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByEmail(String email);
}
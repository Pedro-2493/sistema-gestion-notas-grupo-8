package com.grupo8.sistema_gestion_notas.repository;

import com.grupo8.sistema_gestion_notas.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    List<Student> findByStudentNameContainingIgnoreCase(String studentName);
}
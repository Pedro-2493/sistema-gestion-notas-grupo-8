package com.grupo8.sistema_gestion_notas.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doc_type", nullable = false, length = 20)
    private String docType;

    @Column(name = "doc_number", nullable = false, unique = true, length = 20)
    private String docNumber;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "second_name", length = 50)
    private String secondName;

    @Column(name = "first_last_name", nullable = false, length = 50)
    private String firstLastName;

    @Column(name = "second_last_name", length = 50)
    private String secondLastName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
}
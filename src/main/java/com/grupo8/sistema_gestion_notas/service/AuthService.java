package com.grupo8.sistema_gestion_notas.service;

import com.grupo8.sistema_gestion_notas.Config.JwtUtil;
import com.grupo8.sistema_gestion_notas.dto.AuthResponse;
import com.grupo8.sistema_gestion_notas.dto.LoginRequest;
import com.grupo8.sistema_gestion_notas.dto.RegisterRequest;
import com.grupo8.sistema_gestion_notas.model.entity.Admin;
import com.grupo8.sistema_gestion_notas.model.entity.Student;
import com.grupo8.sistema_gestion_notas.model.entity.Teacher;
import com.grupo8.sistema_gestion_notas.repository.AdminRepository;
import com.grupo8.sistema_gestion_notas.repository.StudentRepository;
import com.grupo8.sistema_gestion_notas.repository.TeacherRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(StudentRepository studentRepository, TeacherRepository teacherRepository,
                       AdminRepository adminRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse login(LoginRequest request) {
        return switch (request.rol()) {
            case "estudiante" -> loginStudent(request);
            case "docente" -> loginTeacher(request);
            case "administrador" -> loginAdmin(request);
            default -> throw new RuntimeException("Rol inválido: " + request.rol());
        };
    }

    private AuthResponse loginStudent(LoginRequest request) {
        Student student = studentRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));
        if (!passwordEncoder.matches(request.password(), student.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }
        String token = jwtUtil.generateToken(student.getEmail(), "estudiante", student.getId(), student.getStudentName());
        return new AuthResponse(token, student.getEmail(), "estudiante", student.getId(), student.getStudentName());
    }

    private AuthResponse loginTeacher(LoginRequest request) {
        Teacher teacher = teacherRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));
        if (!passwordEncoder.matches(request.password(), teacher.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }
        String token = jwtUtil.generateToken(teacher.getEmail(), "docente", teacher.getId(), teacher.getTeacherName());
        return new AuthResponse(token, teacher.getEmail(), "docente", teacher.getId(), teacher.getTeacherName());
    }

    private AuthResponse loginAdmin(LoginRequest request) {
        Admin admin = adminRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));
        if (!passwordEncoder.matches(request.password(), admin.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas");
        }
        String token = jwtUtil.generateToken(admin.getEmail(), "administrador", admin.getId(), admin.getAdminName());
        return new AuthResponse(token, admin.getEmail(), "administrador", admin.getId(), admin.getAdminName());
    }

    public AuthResponse register(RegisterRequest request) {
        if (request.email() == null || request.password() == null || request.nombre() == null || request.rol() == null) {
            throw new RuntimeException("Todos los campos son obligatorios");
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        return switch (request.rol()) {
            case "estudiante" -> registerStudent(request, encodedPassword);
            case "docente" -> registerTeacher(request, encodedPassword);
            case "administrador" -> registerAdmin(request, encodedPassword);
            default -> throw new RuntimeException("Rol inválido: " + request.rol());
        };
    }

    private AuthResponse registerStudent(RegisterRequest request, String encodedPassword) {
        if (studentRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }
        Student student = Student.builder()
                .studentName(request.nombre())
                .email(request.email())
                .password(encodedPassword)
                .document("Pendiente")
                .build();
        student = studentRepository.save(student);
        String token = jwtUtil.generateToken(student.getEmail(), "estudiante", student.getId(), student.getStudentName());
        return new AuthResponse(token, student.getEmail(), "estudiante", student.getId(), student.getStudentName());
    }

    private AuthResponse registerTeacher(RegisterRequest request, String encodedPassword) {
        Teacher teacher = Teacher.builder()
                .teacherName(request.nombre())
                .email(request.email())
                .password(encodedPassword)
                .build();
        teacher = teacherRepository.save(teacher);
        String token = jwtUtil.generateToken(teacher.getEmail(), "docente", teacher.getId(), teacher.getTeacherName());
        return new AuthResponse(token, teacher.getEmail(), "docente", teacher.getId(), teacher.getTeacherName());
    }

    private AuthResponse registerAdmin(RegisterRequest request, String encodedPassword) {
        if (adminRepository.findByEmail(request.email()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }
        Admin admin = Admin.builder()
                .adminName(request.nombre())
                .email(request.email())
                .password(encodedPassword)
                .build();
        admin = adminRepository.save(admin);
        String token = jwtUtil.generateToken(admin.getEmail(), "administrador", admin.getId(), admin.getAdminName());
        return new AuthResponse(token, admin.getEmail(), "administrador", admin.getId(), admin.getAdminName());
    }
}

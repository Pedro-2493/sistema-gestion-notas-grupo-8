-- Estudiantes
INSERT INTO student (student_name, email, document, password) VALUES ('Luisa Contreras', 'luisa@uniedu.co', '1002345678', '{noop}1234');
INSERT INTO student (student_name, email, document, password) VALUES ('Carlos Mejía', 'carlos@uniedu.co', '1009876543', '{noop}1234');
INSERT INTO student (student_name, email, document, password) VALUES ('Ana Sofía Rincón', 'ana@uniedu.co', '1001122334', '{noop}1234');

-- Docentes
INSERT INTO teacher (teacher_name, email, password) VALUES ('Steven Wattson', 'steven@uniedu.co', '{noop}1234');
INSERT INTO teacher (teacher_name, email, password) VALUES ('Laura Mendoza', 'laura@uniedu.co', '{noop}1234');

-- Admin
INSERT INTO admin (admin_name, email, password) VALUES ('Admin Evalix', 'admin@evalix.co', '{noop}1234');

-- Materias
INSERT INTO subject (subject_name, description) VALUES ('Cálculo Diferencial', 'Fundamentos del cálculo diferencial');
INSERT INTO subject (subject_name, description) VALUES ('Estructuras de Datos', 'Organización y manejo de datos');
INSERT INTO subject (subject_name, description) VALUES ('Base de Datos I', 'Introducción a bases de datos relacionales');
INSERT INTO subject (subject_name, description) VALUES ('Inglés Técnico', 'Inglés enfocado al ámbito profesional');

-- Notas (asumiendo ids generados: student=1,2,3 | subject=1,2,3,4 | teacher=1,2)
INSERT INTO grade (value, period, registration_date, student_id, subject_id, teacher_id) VALUES (4.5, '2024-1', '2024-03-15', 1, 1, 1);
INSERT INTO grade (value, period, registration_date, student_id, subject_id, teacher_id) VALUES (3.8, '2024-1', '2024-03-15', 1, 2, 1);
INSERT INTO grade (value, period, registration_date, student_id, subject_id, teacher_id) VALUES (4.0, '2024-1', '2024-03-15', 1, 3, 2);
INSERT INTO grade (value, period, registration_date, student_id, subject_id, teacher_id) VALUES (4.2, '2024-1', '2024-03-15', 1, 4, 2);
INSERT INTO grade (value, period, registration_date, student_id, subject_id, teacher_id) VALUES (4.8, '2024-2', '2024-08-20', 1, 1, 1);
INSERT INTO grade (value, period, registration_date, student_id, subject_id, teacher_id) VALUES (4.1, '2024-2', '2024-08-20', 1, 2, 1);

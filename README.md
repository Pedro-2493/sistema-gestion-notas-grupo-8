# 📚 Sistema de Gestión de Notas — Grupo 8

> Plataforma web con Spring Boot y React para gestionar calificaciones, asistencia y rendimiento académico estudiantil.
---

## 📋 Tabla de Contenidos

- [Introducción](#introducción)
- [Objetivos](#objetivos)
- [Alcance del Proyecto](#alcance-del-proyecto)
- [Tecnologías y Herramientas](#tecnologías-y-herramientas-tech-stack)
- [Seguridad](#seguridad)
- [Integrantes del Equipo](#integrantes-del-equipo)
- [Diagrama de Clases del Dominio](#diagrama-de-clases-del-dominio-v1)
- [Instrucciones de Instalación y Ejecución](#instrucciones-de-instalación-y-ejecución)
- [Documentación de Referencia](#documentación-de-referencia)

---

## Introducción y Contexto

### 📌 Descripción del Problema

En muchos centros educativos, los profesores aún registran las calificaciones de los estudiantes en planillas físicas o archivos dispersos, lo que genera problemas como pérdida de información, dificultad en la actualización de datos y errores en los cálculos de promedios. Este método tradicional representa una carga administrativa adicional para los docentes y limita la trazabilidad y el acceso rápido a la información por parte de los estudiantes.

Por otro lado, los estudiantes suelen depender de consultas presenciales para conocer su desempeño académico, lo que genera demoras y falta de transparencia. La ausencia de un sistema centralizado dificulta la identificación de patrones de rendimiento y el seguimiento del aprendizaje.

### 💡 Justificación

La digitalización de los procesos académicos tiene un impacto directo en tres dimensiones:

- **Social:** mejora la equidad en el acceso a la información académica, permitiendo que estudiantes y familias estén informados en tiempo real sobre el rendimiento escolar.
- **Académica:** facilita la toma de decisiones pedagógicas basadas en datos concretos, favoreciendo el seguimiento individualizado del aprendizaje.
- **Organizacional:** reduce la carga administrativa de los docentes, minimiza errores humanos y centraliza la información en una sola plataforma segura y accesible.

La implementación de este sistema responde a una necesidad real de modernización de los procesos educativos, aportando valor inmediato a instituciones que aún operan con métodos manuales u obsoletos.

### 🏫 Dominio del Proyecto

El sistema opera en el dominio de la **administración académica escolar**. Las entidades principales del negocio son: estudiantes, docentes, materias, calificaciones, asistencia y periodos académicos. El sistema gestiona las relaciones entre estas entidades para ofrecer una visión completa del rendimiento institucional.

---

## Objetivos

### 🎯 Objetivo General

Desarrollar un sistema integral de administración escolar que centralice y digitalice los procesos académicos de una institución educativa, incluyendo la gestión de calificaciones, asistencia, perfiles estudiantiles y reportes de rendimiento, brindando una solución accesible, segura y eficiente tanto para docentes como para estudiantes.

### 📌 Objetivos Específicos

- **OE1 —** Implementar un módulo de gestión de calificaciones que permita el registro, modificación y cálculo automático de promedios por materia y periodo académico.
- **OE2 —** Desarrollar un módulo de control de asistencia que registre y consulte la presencia de los estudiantes con historial detallado por curso y fecha.
- **OE3 —** Construir un módulo de perfiles estudiantiles que centralice la información personal y académica de cada estudiante (hoja de vida).
- **OE4 —** Generar reportes e informes gráficos que visualicen el rendimiento académico individual y grupal, apoyando la toma de decisiones pedagógicas.
- **OE5 —** Proveer una interfaz web intuitiva y responsiva que facilite el uso del sistema por parte de docentes y estudiantes sin requerir conocimientos técnicos avanzados.

---

## Alcance del Proyecto

### ✅ ¿Qué se va a desarrollar?

Los siguientes módulos y funcionalidades están previstos para ser entregados en el semestre:

| Módulo                       | Funcionalidades clave                                                                 |
|------------------------------|---------------------------------------------------------------------------------------|
| Autenticación y roles        | Registro, login y control de acceso diferenciado para docentes y estudiantes          |
| Gestión de calificaciones    | CRUD de notas por materia, periodo y estudiante; cálculo automático de promedios      |
| Control de asistencia        | Registro diario, consulta de historial y reporte por curso y fecha                    |
| Perfil / Hoja de vida        | Información personal y académica centralizada por estudiante                          |
| Reportes e informes gráficos | Visualización del rendimiento mediante gráficas y tablas exportables                  |
| Panel docente                | Vista para administrar cursos, notas y asistencia de sus estudiantes                  |
| Panel estudiantil            | Vista para consultar calificaciones, asistencia y progreso académico personal         |

### ❌ Fuera de Alcance (esta versión)

Los siguientes elementos **no serán desarrollados** en esta primera versión:

- 💳 Módulo de pagos, matrículas o facturación
- 📱 Aplicación móvil nativa (Android / iOS)
- 🔗 Integración con plataformas externas como Moodle, Google Classroom o Blackboard
- 💬 Sistema de mensajería interna entre usuarios

---

## Tecnologías y Herramientas (Tech Stack)

### 🔧 Backend

| Tecnología      | Versión | Descripción                                       |
|-----------------|---------|---------------------------------------------------|
| Java            | 21      | Lenguaje de programación principal                |
| Spring Boot     | 4.0.3   | Framework principal para el desarrollo del backend|
| Spring Web      | —       | Construcción de la API REST                       |
| Spring Data JPA | —       | Persistencia y acceso a base de datos             |
| Spring DevTools | —       | Recarga automática durante el desarrollo          |
| Maven           | —       | Gestión de dependencias y construcción del proyecto|

### 🎨 Frontend

| Tecnología  | Descripción                                             |
|-------------|---------------------------------------------------------|
| React       | Biblioteca para la construcción de interfaces de usuario|
| HTML5 / CSS3| Estructura y estilos base                               |
| Axios       | Cliente HTTP para consumir la API REST desde el frontend|

### 🗄️ Base de Datos

| Tecnología | Descripción                                                              |
|------------|--------------------------------------------------------------------------|
| PostgreSQL | Motor de base de datos relacional principal (producción / desarrollo)    |
| H2         | Base de datos en memoria para pruebas rápidas y entornos de testing      |

### 🛠️ Otras Herramientas

| Herramienta             | Uso                                              |
|-------------------------|--------------------------------------------------|
| Git & GitHub            | Control de versiones y colaboración en equipo    |
| Postman / Swagger UI    | Pruebas y documentación de endpoints de la API   |
| IntelliJ IDEA / VS Code | Entorno de desarrollo (IDE)                      |
| Draw.io                 | Diseño de diagramas de dominio y arquitectura    |

> **Nota sobre el paquete:** El nombre original `com.grupo8.sistema-gestion-notas` es inválido en Java. Este proyecto utiliza `com.grupo8.sistema_gestion_notas` como identificador del paquete.

---

## Seguridad

Este proyecto ha pasado por un proceso de auditoría de seguridad, documentado en el historial de commits.

### Endpoints públicos (sin autenticación)

| Endpoint | Descripción |
|----------|-------------|
| `POST /api/auth/login` | Inicio de sesión |
| `POST /api/auth/register` | Registro de nuevos usuarios — siempre crea una cuenta de tipo **estudiante**, sin importar los datos enviados |
| `/swagger-ui/**`, `/api-docs/**` | Documentación interactiva de la API |

> ⚠️ Swagger UI se deja público intencionalmente en este entorno de portafolio/demo, para que cualquiera pueda explorar la API sin herramientas adicionales. **En un entorno de producción real, esta ruta debería cerrarse.**

### Endpoints protegidos (requieren JWT)

Todo lo que no esté en la tabla anterior requiere un header `Authorization: Bearer <token>`, obtenido tras iniciar sesión.

Adicionalmente, la creación, edición y eliminación de **docentes** y **administradores** exige que el token pertenezca a un usuario con rol `ADMINISTRADOR` (`@PreAuthorize`).

### Hallazgos corregidos

| # | Hallazgo | Estado |
|---|----------|--------|
| 1 | Toda la API estaba abierta pese a tener JWT configurado (`permitAll()` global) | ✅ Corregido |
| 2 | El registro público permitía elegir libremente el rol (`estudiante`, `docente`, `administrador`), incluyendo crear cuentas admin sin autorización | ✅ Corregido |

> El detalle técnico de cada corrección está en los commits `fix(security): ...` del historial de Git.

---

## Integrantes del Equipo

| Nombre           | Rol principal | Usuario GitHub    |
|------------------|---------------|-------------------|
| Pedro Zamora     | Líder         | @pedro-2493       |
| Didier Achury    | Developer     | @esteban95-a      |
| Mariana Ardila   | Developer     | @mariana-ardila   |
| Kevin Vélez      | Developer     | @kevinn-9         |

---

## Diagrama de Clases del Dominio (v1)

![Diagrama de dominio v1](docs/diagrama-dominio-v1.png)

> 📌 _Diagrama inicial del dominio — versión 1. Se actualizará en futuras entregas conforme avance el desarrollo._

Si aún no tienes la imagen generada, a continuación se muestra la estructura textual del dominio:

```
┌─────────────┐         ┌──────────────┐         ┌─────────────┐
│  Estudiante │         │     Nota     │         │   Materia   │
│─────────────│1      n │──────────────│n       1│─────────────│
│ id          │────────►│ id           │◄────────│ id          │
│ nombre      │         │ valor        │         │ nombre      │
│ apellido    │         │ periodo      │         │ descripcion │
│ email       │         │ fechaRegistro│         │ creditos    │
│ documento   │         │ estudianteId │         └─────────────┘
└─────────────┘         │ materiaId    │
       │                │ docenteId    │
       │1               └──────────────┘
       │
       ▼n
┌──────────────┐         ┌─────────────┐
│  Asistencia  │         │   Docente   │
│──────────────│         │─────────────│
│ id           │         │ id          │
│ fecha        │         │ nombre      │
│ estado       │         │ apellido    │
│ estudianteId │         │ email       │
│ materiaId    │         │ especialidad│
└──────────────┘         └─────────────┘
```

---

## Instrucciones de Instalación y Ejecución para Desarrolladores

Sigue estos pasos para ejecutar el proyecto en tu entorno local de desarrollo.

### Prerrequisitos

Asegúrate de tener instalado lo siguiente antes de comenzar:

- ☑️ Java JDK 21
- ☑️ Maven 3.8+
- ☑️ PostgreSQL 15+ _(o usar H2 para pruebas rápidas)_
- ☑️ Node.js 18+ _(para el frontend en React)_
- ☑️ Git

---

### 1. Clonar el Repositorio

```bash
git clone https://github.com/Pedro-2493/sistema-gestion-notas-grupo-8.git
```

---

### 2. Entrar al Directorio del Proyecto

```bash
cd sistema-gestion-notas-grupo-8
```

---

### 3. Configurar la Base de Datos

Edita el archivo `src/main/resources/application-dev.properties` con la opción que prefieras:

**Opción A — H2 (para pruebas rápidas, sin instalar nada):**

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

> 💡 Con H2 activo, puedes acceder a la consola web en: `http://localhost:8080/h2-console`

**Opción B — PostgreSQL (entorno de desarrollo / producción):**

Primero crea la base de datos:

```sql
CREATE DATABASE sistema_gestion_notas;
```

Luego configura el archivo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/sistema_gestion_notas
spring.datasource.username=[tu_usuario]
spring.datasource.password=[tu_contraseña]
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
```

---

### 4. Ejecutar el Backend

```bash
mvn clean install
./mvnw spring-boot:run
```

El servidor estará disponible en: **`http://localhost:8080`**

---

### 5. Ejecutar el Frontend (React)

```bash
cd frontend
npm install
npm start
```

La interfaz estará disponible en: **`http://localhost:3000`**

---

### 6. Verificar que el Servidor Está Activo

```bash
GET http://localhost:8080/actuator/health
```

---

## Documentación de Referencia

- [Documentación oficial de Apache Maven](https://maven.apache.org/guides/index.html)
- [Guía del Plugin Maven de Spring Boot](https://docs.spring.io/spring-boot/4.0.3/maven-plugin)
- [Spring Web — Servicios REST](https://docs.spring.io/spring-boot/4.0.3/reference/web/servlet.html)
- [Spring Data JPA](https://docs.spring.io/spring-boot/4.0.3/reference/data/sql.html#data.sql.jpa-and-spring-data)
- [Spring Boot DevTools](https://docs.spring.io/spring-boot/4.0.3/reference/using/devtools.html)
- [Construir un servicio RESTful con Spring](https://spring.io/guides/gs/rest-service/)
- [Acceder a datos con JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [Documentación oficial de React](https://react.dev/)

---

> 📁 Proyecto desarrollado por **Grupo 8** | Técnico Laboral en Desarrollo de Software

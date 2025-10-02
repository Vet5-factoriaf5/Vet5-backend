# 🐾 HappyPaws - Backend

Este proyecto corresponde al **sistema de gestión de pacientes** para la clínica veterinaria de Margarita.  
El backend está desarrollado en **Java con Spring Boot**, y expone una API REST que permite gestionar usuarios, pacientes, citas y tratamientos.

---

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Data JPA (Hibernate)
- Spring Security (autenticación y autorización con JWT)
- MySQL
- Maven

---

## ⚙️ Funcionalidades implementadas

- Registro de usuarios.
- Login con validación de credenciales.
- Roles: **Administrador** y **Cliente**.
- Seguridad con JWT (JSON Web Token).
- Relación entre usuarios, pacientes, citas y tratamientos.

---

## 🗄️ Modelo de datos

La base de datos se ha diseñado siguiendo un modelo relacional.  
Diagrama en formato Mermaid:

```mermaid
erDiagram
    ROLES ||--o{ USERS : has
    USERS ||--o{ PATIENTS : "is tutor of"
    USERS ||--o{ APPOINTMENTS : "requests/attends"
    PATIENTS ||--o{ APPOINTMENTS : "has"
    PATIENTS ||--o{ TREATMENTS : "receives"
    USERS ||--o{ TREATMENTS : "performs"

    ROLES {
        int id
        varchar name
    }

    USERS {
        int id
        varchar email
        varchar password_hash
        varchar fullname
        varchar phone
        varchar dni
        varchar role_name
        datetime created_at
        datetime updated_at
    }

    PATIENTS {
        int id
        varchar name
        date birth_date
        varchar breed
        varchar gender
        varchar id_number
        int tutor_user_id
        varchar tutor_fullname
        varchar tutor_phone
        datetime created_at
        datetime updated_at
    }

    APPOINTMENTS {
        int id
        datetime appointment_datetime
        int patient_id
        int requested_by_user_id
        varchar appointment_type
        varchar reason
        varchar status
        datetime created_at
        datetime updated_at
    }

    TREATMENTS {
        int id
        int patient_id
        text treatment_desc
        datetime treatment_date
        int performed_by_user_id
        text notes
        datetime created_at
    }
```

---

## 📦 Instalación y ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu-repo/backend.git
   ```
2. Entrar al directorio:
   ```bash
   cd backend
   ```
3. Configurar la base de datos en `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/happypaws
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_password
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Compilar y ejecutar:
   ```bash
   mvn spring-boot:run
   ```

---

## 🔑 Endpoints principales

- `POST /api/auv1th/register` → Registro de usuario  
- `POST /api/v1/login` → Login de usuario

---

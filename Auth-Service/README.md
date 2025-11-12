# 🧩 Auth Service — Spring Boot Authentication Microservice

This project is the **Authentication Service** for a modular **Digital Banking System**, built using **Spring Boot**, **Spring Security**, **JWT**, and **MySQL**.  
It handles user registration, login, and token-based authentication.

---

## 🚀 Features

- 🔐 User registration & login
- 🧾 JWT-based authentication
- 🧍 Custom `UserDetailsService` implementation
- 🗃️ MySQL database integration
- ⚙️ Configurable via `application.yml`
- 🧱 Built with Maven for easy dependency management

---

## 🏗️ Tech Stack

| Layer      | Technology                  |
| ---------- | --------------------------- |
| Language   | Java 17                     |
| Framework  | Spring Boot 3               |
| Security   | Spring Security + JWT       |
| Database   | MySQL 8                     |
| Build Tool | Maven                       |
| ORM        | Spring Data JPA / Hibernate |

---

## 📁 Project Structure

Auth-Service/
├── src/main/java/com/bank/authservice/
│ ├── controller/ # REST controllers (Auth endpoints)
│ ├── config/ # Security & JWT configuration
│ ├── model/ # Entity models (User, Role)
│ ├── repository/ # JPA repositories
│ ├── service/ # Business logic (UserDetails, JWT)
│ └── AuthServiceApplication.java # Main application
├── src/main/resources/
│ └── application.yml # Configuration file
├── pom.xml # Maven dependencies
└── README.md

---

## ⚙️ Configuration

Update your `src/main/resources/application.yml` with your local database credentials:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/auth_service?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    username: root
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate.dialect: org.hibernate.dialect.MySQL8Dialect
    show-sql: true

server:
  port: 8081

jwt:
  secret: replace_this_with_a_long_random_secret_key
  expiration-ms: 3600000

Then create the database manually:

CREATE DATABASE auth_service;


🧰 Build & Run

Make sure you have Maven and MySQL running locally.


# Build the project
mvn clean package

# Run the service
mvn spring-boot:run


✅ Once started, you’ll see logs like:

Tomcat started on port 8081 (http)
Started AuthServiceApplication


🔗 API Endpoints
🧍 Register User

POST /api/auth/register

Request Body:

{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "password123",
  "role": "USER"
}
```

Response:
{
"message": "User registered successfully"
}

🔑 Login

POST /api/auth/login

Request Body:

{
"username": "john_doe",
"password": "password123"
}

{
"token": "eyJhbGciOiJIUzI1NiJ9...",
"type": "Bearer",
"username": "john_doe"
}

🔒 Access Protected Endpoint

Example protected route (if implemented):

GET /api/users/me
Headers:

makefile
Authorization: Bearer <your_token>

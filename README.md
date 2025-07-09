# Auth Security Practise

## Overview
This project is a Spring Boot application that demonstrates authentication and authorization practices using JWT, role-based access control, and Spring Security. It includes user registration, login, and role-based access to endpoints.

## Features
- **User Registration**: Allows users to sign up with default `USER` role.
- **User Login**: Authenticates users and generates a JWT token.
- **Role-Based Access Control**: Restricts access to endpoints based on user roles (`USER`, `ADMIN`, `SUPER_ADMIN`).
- **Database Seeding**: Automatically seeds roles and admin users into the database.
- **JWT Authentication**: Secures endpoints using JSON Web Tokens.

## Technologies Used
- **Java**: Programming language.
- **Spring Boot**: Framework for building the application.
- **Spring Security**: For authentication and authorization.
- **JWT**: For token-based authentication.
- **Maven**: Dependency management.
- **BCrypt**: For password hashing.
- **Lombok**: Reduces boilerplate code.
- **H2 Database**: In-memory database for development.

## Project Structure
- `src/main/java/com/binitshrestha/Auth_Security_Practise/config`: Contains configuration classes for Spring Security and application setup.
- `src/main/java/com/binitshrestha/Auth_Security_Practise/controller`: Contains REST controllers for handling authentication and admin-related operations.
- `src/main/java/com/binitshrestha/Auth_Security_Practise/service`: Contains service classes for business logic.
- `src/main/java/com/binitshrestha/Auth_Security_Practise/model`: Contains data transfer objects (DTOs) and entity classes.
- `src/main/java/com/binitshrestha/Auth_Security_Practise/repository`: Contains repository interfaces for database operations.
- `src/main/java/com/binitshrestha/Auth_Security_Practise/bootstrap`: Contains classes for seeding initial data into the database.

## Endpoints
### Authentication
- **POST `/auth/signup`**: Registers a new user.
  - Request Body: `RegisterUserDto`
  - Response: `User`
- **POST `/auth/login`**: Authenticates a user and returns a JWT token.
  - Request Body: `LoginUserDto`
  - Response: `LoginResponse`

### Admin
- **POST `/admin/createAdministrator`**: Creates a new administrator (requires `SUPER_ADMIN` role).
  - Request Body: `RegisterUserDto`
  - Response: `ResponseEntity`

## Database Seeding
The application seeds roles and admin users into the database using the following classes:
- **`RoleSeeder`**: Seeds roles (`USER`, `ADMIN`, `SUPER_ADMIN`).
- **`AdminSeeder`**: Seeds a default `SUPER_ADMIN` user.

# Demo3 — JWT Login Implementation (Spring Boot)

This module provides a stateless login mechanism using **JWT (JSON Web Tokens)** with Spring Security.

It uses the [auth0/java-jwt](https://github.com/auth0/java-jwt) library for token creation and verification, and implements secure authentication using **RSA256 key pairs**.

---

## 🔧 Tech Stack

- Java 17
- Spring Boot 3
- Spring Security
- [Auth0 Java JWT](https://github.com/auth0/java-jwt)
- Maven / Gradle

---

## ✨ Features

- 🔐 **JWT-based Authentication**
  - Token generation and parsing via `JwtUtil`
  - Asymmetric encryption (RSA256 public/private key)
- 🧱 **Custom Security Configuration**
  - `SecurityConfig` defines stateless login behavior
  - `JwtAuthenticationFilter` processes and validates tokens
- 👤 **User Management**
  - Basic user entity and login endpoint
  - Stateless session using HTTP headers
- ✅ **Tested with JUnit**

---

## 🚀 Getting Started

### 1. Run the Application

```bash
./gradlew bootRun

Main class: Demo33Application.java

### 2. Obtain JWT Token

	-	POST /api/auth/login
with JSON body:

{
  "username": "testuser",
  "password": "123456"
}

- Response:

{
  "accessToken": "eyJhbGciOiJSUzI1NiIsInR5cCI6..."
}

### 3. Use JWT in Requests

Include token in header:

Authorization: Bearer <your-token>

🗂 Project Structure

demo3/
├── config/                  # Spring Security setup
├── controller/              # Auth endpoints
├── entity/                  # User entity
├── jwt/                     # JWT logic (JwtUtil, Filters)
├── repository/              # UserRepository
├── service/                 # UserService
└── resources/application.properties


⸻

🛠 Planned Features (Coming Soon)

The following features are currently being designed and will be implemented step by step:
	•	🔄 Logout Endpoint
	•	Invalidate token client-side or blacklist server-side
	•	♻️ Token Refresh
	•	Refresh token mechanism for re-issuing access tokens
	•	📦 DTO Refactoring
	•	Use of LoginRequestDTO, UserResponseDTO, etc. for clearer API boundaries
	•	👥 Concurrent Session Management
	•	Track and manage simultaneous logins per user/token
	•	🚀 System Extensibility
	•	Abstract token provider / key config
	•	Prepare for Redis, external user DB, and multi-service auth

📚 Reference
	•	Auth0 Java JWT Docs
	•	Spring Security JWT Guide

⸻

🤝 Contribution

This project is for learning and demonstration purposes.
Feel free to fork, adapt, or contribute via pull request.







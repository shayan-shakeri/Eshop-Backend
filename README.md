# EShop Backend API

A high-performance, containerized e-commerce backend built with Kotlin, Ktor, and HikariCP database connection pooling. Optimized for decoupled microservice architectures and lightweight cloud deployments.

---

## 🚀 Features & Architecture
* **Ktor Server Core:** Light asynchronous engine for fast routing and serialization.
* **Robust Feature Modules:** Fully isolated architectures for Banners, Categories, Filters, Product Images, Support Messages, and User Profiles.
* **Docker Native:** Cloud-ready container image based on clean, alpine openjdk runtimes.
* **HikariCP Integration:** Production-grade database connection management.

---

## 🛠️ Local Development Setup

### Prerequisites
* Java 21 (LTS)
* Docker Desktop
* Gradle 9.x

### 1. Environment Variable Configuration
Create a `.env` file in the root project folder. Ensure local connections are mapped properly via the Docker host bridge if containerizing:

```env
# Database
DB_URL=jdbc:mysql://host.docker.internal:3306/eshop
DB_USER=root
DB_PASSWORD=test
DB_POOL_SIZE=10

# Password Hashing
HASH_ITERATIONS=10000
HASH_ALGORITHM=PBKDF2WithHmacSHA256

# JWT Secrets
ACCESS_TOKEN_SECRET=test
REFRESH_TOKEN_SECRET=test

# JWT Configuration
TOKEN_ISSUER=eshop
TOKEN_AUDIENCE=eshop-users

# Token Expiration (milliseconds)
ACCESS_USER_EXP=3600000
ACCESS_EMPLOYEE_EXP=28800000
REFRESH_EXP=2592000000

# Resend Email Token
EMAIL_SENDER_API_KEY=test
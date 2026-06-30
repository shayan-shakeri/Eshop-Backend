# EShop Backend

A modern, production-ready e-commerce backend built with **Kotlin** and **Ktor 3**. The project follows a layered architecture using services, repositories, Exposed ORM, dependency injection with Koin, and JWT authentication.

---

## Features

### Authentication
- User registration
- User login
- JWT authentication
- Email verification
- Password hashing with salt
- Version verification

### User Management
- User profile
- User addresses
- User images
- Notifications
- Favorites

### Product Management
- Products
- Categories
- Filters
- Banners
- Product comments
- Product questions
- Product answers
- Price alerts

### Orders
- Orders
- Order products
- Delivery tracking
- Monthly financial reports

### Employee Management
- Employees
- Employee roles
- Salaries

### Support
- Real-time support chat
- WebSocket messaging

### Administration
- Audit logs
- Error logging
- Version control
- Monthly financial statistics

### Infrastructure
- Image upload system
- Custom exception handling
- Global StatusPages
- Request validation
- Dependency Injection (Koin)
- Exposed ORM
- MySQL
- Docker support

---

## Tech Stack

- Kotlin
- Ktor 3
- Exposed ORM
- MySQL
- Koin
- JWT
- WebSockets
- Kotlinx Serialization
- Docker

---

## Architecture

```
Client
   │
Routes
   │
Services
   │
Repositories
   │
Exposed ORM
   │
MySQL
```

The project follows a layered architecture where:

- Routes handle HTTP requests.
- Services contain business logic.
- Repositories handle database operations.
- Exposed manages communication with MySQL.

---

## Project Structure

```
feature/
├── auth/
├── user/
├── employee/
├── product/
├── category/
├── filter/
├── banner/
├── favorite/
├── notification/
├── question/
├── answer/
├── comment/
├── order/
├── order_product/
├── support_chat/
├── support_message/
├── version_control/
├── monthly_log/
├── audit_logs/
├── error_log/
└── ...
```

---

## Running the Project

### Clone

```bash
git clone <https://github.com/shayan-shakeri/Eshop-Backend>
```

### Build

```bash
./gradlew build
```

### Run

```bash
./gradlew run
```

---

## Docker

### Build Image

```bash
docker build -t eshop-backend .
```

### Run Container

```bash
docker run -d \
  --name eshop \
  -p 8080:8080 \
  eshop-backend
```

---

## Database

The backend uses **MySQL** together with **Exposed ORM**.

Main modules include:

- Users
- Products
- Categories
- Filters
- Orders
- Order Products
- Employees
- Notifications
- Favorites
- Questions
- Answers
- Comments
- Support Chats
- Support Messages
- Monthly Logs
- Audit Logs
- Version Control
- Error Logs

---

## Security

- JWT Authentication
- Password hashing with salt
- Request validation
- Custom exception handling
- Audit logging

---

## License

This project was developed for educational purposes as an end-term project and serves as a complete example of a modern Kotlin/Ktor e-commerce backend.
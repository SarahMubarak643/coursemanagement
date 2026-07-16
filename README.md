# Course Management System

A simple Spring Boot application built for the COOP Training Spring Boot Assignment.
It manages one entity, **Course**, through a REST API and a small Thymeleaf web page,
using MySQL as the database.

## Technologies Used

- Java 21
- Spring Boot 3.3.4
- Maven
- MySQL
- Spring Data JPA (Hibernate)
- Spring Security (database authentication, BCrypt)
- Spring MVC + Thymeleaf
- Bean Validation
- Swagger (springdoc-openapi)
- Spring Boot Actuator

## How to Run

1. Make sure MySQL is running on your machine.
2. Update the username/password in `application.properties` if needed.
3. Run the project:

```
mvn spring-boot:run
```

4. The app will start at: **http://localhost:8081**

| Link | What it shows |
|---|---|
| `/courses` | Web page listing all courses |
| `/courses/add` | Form to add a new course |
| `/swagger-ui.html` | Test the REST API |
| `/actuator/health` | Server health check |

## Default Users

All demo users use the password: **password123**

| Username | Role | Can do |
|---|---|---|
| employee1 | EMPLOYEE | View only (GET) |
| manager1 | MANAGER | View, create, update |
| admin1 | ADMIN | Everything, including delete |

## REST API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | /api/courses | Get all courses (supports `?sort=price,asc`) |
| GET | /api/courses/{id} | Get one course by id |
| GET | /api/courses/category/{category} | Filter courses by category |
| POST | /api/courses | Create a new course |
| PUT | /api/courses/{id} | Fully update a course |
| PATCH | /api/courses/{id} | Partially update a course |
| DELETE | /api/courses/{id} | Delete a course |

**Example course (JSON):**
```json
{
  "name": "Introduction to Java",
  "category": "Programming",
  "price": 199.99,
  "durationHours": 30,
  "level": "BEGINNER",
  "active": true,
  "code": "CSE-101"
}
```

Notes:
- `level` must be `BEGINNER`, `INTERMEDIATE`, or `ADVANCED`.
- `code` must match the format `ABC-101` (3 letters, dash, 3 numbers).

**Error example (course not found):**
```json
{
  "status": 404,
  "message": "Course not found with id: 100"
}
```

## Project Structure

```
entity/        → Course.java
repository/     → CourseRepository.java
service/        → CourseService, NotificationService
controller/     → REST API + Thymeleaf controllers
security/       → SecurityConfig
exception/      → Error handling (404, validation errors)
validation/     → Custom validation for the "level" field
config/         → AppConfig (Bean + Lazy example)
resources/      → application.properties, HTML pages, CSS
```

## Testing with Postman

A Postman collection is included in the `postman` folder. It contains:
- GET, POST, PUT, PATCH, DELETE requests
- A 404 test (course that doesn't exist)
- A 403 test (employee trying to delete, which is not allowed)

Import the collection into Postman and use Basic Auth with the users above.
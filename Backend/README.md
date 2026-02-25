# Heroes & Villains Blog API

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.11-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15+-blue.svg)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-red.svg)](https://maven.apache.org/)

Backend REST API for the **Heroes & Villains Blog** application built with Spring Boot. This API allows you to manage heroes, villains, anti-heroes, categories, blogs, and comments.

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

- RESTful API architecture
- CRUD operations for Heroes, Villains, Anti-Heroes, Categories, Blogs, and Comments
- Character categorization (Marvel, DC, Anime, etc.)
- Character attributes: powers, affiliations, ratings, biographies
- Blog posts linked to characters
- Comment system for blogs
- PostgreSQL database integration
- JPA/Hibernate ORM with entity relationships
- Input validation with Jakarta Validation
- Global exception handling
- OpenAPI/Swagger documentation
- Profile-based configuration (dev, test, prod)

## 🛠 Tech Stack

| Technology | Version | Description |
|------------|---------|-------------|
| Java | 17 | Programming Language |
| Spring Boot | 3.5.11 | Application Framework |
| Spring Data JPA | - | Data Access Layer |
| PostgreSQL | 15+ | Relational Database |
| Lombok | - | Boilerplate Reduction |
| Maven | 3.9+ | Build Tool |
| OpenAPI | 3.0 | API Documentation |

## 📦 Prerequisites

Before running this application, ensure you have the following installed:

- **JDK 17** or higher
- **Maven 3.9+**
- **PostgreSQL 15+** (running locally or accessible remotely)
- **Git** (for cloning the repository)

## 🚀 Installation

### 1. Clone the repository

```bash
git clone <repository-url>
cd Backend
```

### 2. Create PostgreSQL Database

```sql
CREATE DATABASE heroDB;
```

### 3. Configure Environment Variables

Create a `.env` file in the project root:

```env
DB_NAME=heroDB
DB_USERNAME=postgres
DB_PASSWORD=your_password
```

### 4. Build the project

```bash
./mvnw clean install
```

## ⚙️ Configuration

### Application Properties

The application uses profile-based configuration:

| Profile | File | Description |
|---------|------|-------------|
| `dev` | `application-dev.properties` | Development (PostgreSQL) |
| `test` | `application-test.properties` | Testing (H2 in-memory) |
| `prod` | `application-prod.properties` | Production |

### Active Profile

Set the active profile in `application.properties`:

```properties
spring.profiles.active=dev
```

### Database Configuration

Default development configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

## 🏃 Running the Application

### Using Maven

```bash
# Development mode
./mvnw spring-boot:run

# With specific profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### Using JAR

```bash
# Build JAR
./mvnw clean package

# Run JAR
java -jar target/Backend-0.0.1-SNAPSHOT.jar
```

The application will be available at: `http://localhost:8080`

## 📖 API Documentation

### Swagger UI

Access the interactive API documentation at:

```
http://localhost:8080/swagger-ui.html
```

### OpenAPI Specification

```
http://localhost:8080/v3/api-docs
```

### Available Endpoints

#### Categories

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/categories` | Get all categories |
| `GET` | `/api/v1/categories/{id}` | Get category by ID |
| `POST` | `/api/v1/categories` | Create a new category |
| `PUT` | `/api/v1/categories/{id}` | Update a category |
| `DELETE` | `/api/v1/categories/{id}` | Delete a category |

#### Heroes

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/heroes` | Get all heroes |
| `GET` | `/api/v1/heroes/{id}` | Get hero by ID |
| `POST` | `/api/v1/heroes` | Create a new hero |
| `PUT` | `/api/v1/heroes/{id}` | Update a hero |
| `DELETE` | `/api/v1/heroes/{id}` | Delete a hero |

#### Villains

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/villains` | Get all villains |
| `GET` | `/api/v1/villains/{id}` | Get villain by ID |
| `POST` | `/api/v1/villains` | Create a new villain |
| `PUT` | `/api/v1/villains/{id}` | Update a villain |
| `DELETE` | `/api/v1/villains/{id}` | Delete a villain |

#### Anti-Heroes

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/anti-heroes` | Get all anti-heroes |
| `GET` | `/api/v1/anti-heroes/{id}` | Get anti-hero by ID |
| `POST` | `/api/v1/anti-heroes` | Create a new anti-hero |
| `PUT` | `/api/v1/anti-heroes/{id}` | Update an anti-hero |
| `DELETE` | `/api/v1/anti-heroes/{id}` | Delete an anti-hero |

#### Blogs

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/blogs` | Get all blogs |
| `GET` | `/api/v1/blogs/{id}` | Get blog by ID |
| `POST` | `/api/v1/blogs` | Create a new blog |
| `PUT` | `/api/v1/blogs/{id}` | Update a blog |
| `DELETE` | `/api/v1/blogs/{id}` | Delete a blog |

#### Comments

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/comments` | Get all comments |
| `GET` | `/api/v1/comments/{id}` | Get comment by ID |
| `GET` | `/api/v1/comments/blog/{blogId}` | Get comments by blog |
| `POST` | `/api/v1/comments` | Create a new comment |
| `PUT` | `/api/v1/comments/{id}` | Update a comment |
| `DELETE` | `/api/v1/comments/{id}` | Delete a comment |

### Example Requests

#### Create a Category

```bash
curl -X POST http://localhost:8080/api/v1/categories \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Marvel",
    "description": "Marvel Universe characters"
  }'
```

#### Create a Hero

```bash
curl -X POST http://localhost:8080/api/v1/heroes \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Peter",
    "lastName": "Parker",
    "heroName": "Spider-Man",
    "power": "Spider abilities, web-slinging",
    "affiliation": "Avengers",
    "rating": 4.8,
    "imageUrl": "https://example.com/spiderman.jpg",
    "biography": "Bitten by a radioactive spider...",
    "categoryId": "uuid-of-marvel-category"
  }'
```

#### Create a Villain

```bash
curl -X POST http://localhost:8080/api/v1/villains \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Norman",
    "lastName": "Osborn",
    "villainName": "Green Goblin",
    "power": "Enhanced strength, glider, pumpkin bombs",
    "affiliation": "Sinister Six",
    "rating": 4.5,
    "imageUrl": "https://example.com/greengoblin.jpg",
    "biography": "Industrialist turned villain...",
    "categoryId": "uuid-of-marvel-category"
  }'
```

#### Create a Blog

```bash
curl -X POST http://localhost:8080/api/v1/blogs \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Amazing Spider-Man: A Hero Analysis",
    "body": "Spider-Man is one of the most beloved heroes...",
    "author": "John Doe",
    "coverImageUrl": "https://example.com/blog-cover.jpg",
    "tags": "spiderman,marvel,heroes",
    "heroId": "uuid-of-hero"
  }'
```

#### Create a Comment

```bash
curl -X POST http://localhost:8080/api/v1/comments \
  -H "Content-Type: application/json" \
  -d '{
    "authorName": "Jane Doe",
    "content": "Great article! Spider-Man is my favorite hero.",
    "blogId": "uuid-of-blog"
  }'
```

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/springbootlearning/learningspringboot/backend/
│   │   ├── BackendApplication.java    # Main application entry point
│   │   ├── config/                    # Configuration classes
│   │   │   └── OpenAPIConfig.java
│   │   ├── controller/                # REST Controllers
│   │   │   ├── CategoryController.java
│   │   │   ├── HeroController.java
│   │   │   ├── VillainController.java
│   │   │   ├── AntiHeroController.java
│   │   │   ├── BlogController.java
│   │   │   └── CommentController.java
│   │   ├── service/                   # Business Logic Layer
│   │   │   ├── CategoryService.java
│   │   │   ├── HeroService.java
│   │   │   ├── VillainService.java
│   │   │   ├── AntiHeroService.java
│   │   │   ├── BlogService.java
│   │   │   └── CommentService.java
│   │   ├── repository/                # Data Access Layer
│   │   │   ├── CategoryRepository.java
│   │   │   ├── HeroRepository.java
│   │   │   ├── VillainRepository.java
│   │   │   ├── AntiHeroRepository.java
│   │   │   ├── BlogRepository.java
│   │   │   └── CommentRepository.java
│   │   ├── entity/                    # JPA Entities
│   │   │   ├── Category.java
│   │   │   ├── Hero.java
│   │   │   ├── Villain.java
│   │   │   ├── AntiHero.java
│   │   │   ├── Blog.java
│   │   │   └── Comment.java
│   │   ├── dto/                       # Data Transfer Objects
│   │   │   ├── CategoryDto.java
│   │   │   ├── HeroDto.java
│   │   │   ├── VillainDto.java
│   │   │   ├── AntiHeroDto.java
│   │   │   ├── BlogDto.java
│   │   │   └── CommentDto.java
│   │   └── exception/                 # Exception Handling
│   │       ├── NotFoundException.java
│   │       └── GlobalExceptionHandler.java
│   └── resources/
│       ├── application.properties     # Main configuration
│       ├── application-dev.properties # Development profile
│       └── application-test.properties # Test profile
└── test/                              # Test sources
    └── java/
        └── com/springbootlearning/learningspringboot/backend/
            ├── service/
            │   └── VillainServiceTest.java
            └── controller/
                └── VillainControllerTest.java
```

## 🧪 Testing

### Run all tests

```bash
./mvnw test
```

### Run with coverage

```bash
./mvnw test jacoco:report
```

### Test Categories

| Category | Command |
|----------|---------|
| Unit Tests | `./mvnw test -Dtest=*Test` |
| Integration Tests | `./mvnw verify -Dtest=*IT` |

## 🔧 Development

### Code Style

This project follows standard Java naming conventions and Spring Boot best practices:

- **Classes**: PascalCase (e.g., `HeroService`)
- **Methods**: camelCase (e.g., `findHeroById`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `MAX_PAGE_SIZE`)
- **Packages**: lowercase (e.g., `com.springbootlearning.backend`)

### Database Migrations

The application uses Hibernate's `ddl-auto=update` for development. For production, consider using:

- [Flyway](https://flywaydb.org/)
- [Liquibase](https://www.liquibase.org/)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## 📚 Additional Resources

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Building REST APIs with Spring](https://spring.io/guides/gs/rest-service/)

---

**Author:** Heroes & Villains Blog Team  
**Version:** 1.0.0  
**Last Updated:** 2025

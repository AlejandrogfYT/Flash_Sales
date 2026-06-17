# Flash Sales

REST API for managing flash sales events, products, tickets, and transactions. Built with Spring Boot 4.0, Java 21, and PostgreSQL.

## Tech Stack

- **Java 21**
- **Spring Boot 4.0.6** (WebMVC, Data JPA, Validation, Redis)
- **PostgreSQL 15** (via Docker)
- **Maven**

## Prerequisites

- Java 21+
- Docker & Docker Compose
- Maven (or use the included `mvnw` wrapper)

## Setup

### 1. Database

```bash
cp .env.template .env
# Edit .env with your credentials (or use the defaults)
docker compose up -d
```

### 2. Run

```bash
./mvnw spring-boot:run
```

The API starts at `http://localhost:8080`.

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/register` | Register a user |
| GET | `/users` | List all users |
| GET | `/users/{id}` | Get user by ID |
| PUT | `/users/{id}` | Update user |
| DELETE | `/users/{id}` | Delete user |
| GET/POST | `/events` | List / Create events |
| GET/PUT/DELETE | `/events/{id}` | Get / Update / Delete event |
| GET/POST | `/products` | List / Create products |
| GET/PUT/DELETE | `/products/{id}` | Get / Update / Delete product |
| GET/POST | `/tickets` | List / Create tickets |
| GET/PUT/DELETE | `/tickets/{id}` | Get / Update / Delete ticket |
| GET/POST | `/transactions` | List / Create transactions |
| GET/PUT/DELETE | `/transactions/{id}` | Get / Update / Delete transaction |

## Project Structure

```
src/main/java/org/example/flash_sales/
├── Controllers/       # REST controllers
├── DTOs/              # Request/response records
├── Enums/             # Status, UserType
├── Models/            # JPA entities
├── Repositories/      # Spring Data JPA repositories
└── Services/          # Business logic layer
```

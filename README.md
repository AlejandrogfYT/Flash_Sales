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

## Authentication

Uses **Keycloak** as the OAuth2/JWT issuer (`myrealm`). All requests (except `/register` and `/login`) require a valid JWT in the `Authorization: Bearer <token>` header.

| Endpoint | Auth | Notes |
|----------|------|-------|
| `POST /register` | Public | Creates user in Keycloak |
| `POST /login` | Public | Returns JWT (`access_token`, `refresh_token`) |
| All other endpoints | Authenticated | Any valid JWT holder |

Role-based or ownership-based authorization is **not enforced** — every authenticated user has the same access.

Users are synced to the local `users` table on first authenticated request via `UserSyncFilter`.

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

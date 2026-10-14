# Bancosol Lite

Web application to coordinate food-donation campaigns: volunteers register
donations at collection points, managers verify them and follow each
campaign's progress. Course project, Web Application Programming (WUT).

**Status:** work in progress (Early Pass). See `docs/` for the proposal.

## Stack
Spring Boot 3 · Java 17 · Thymeleaf · Spring Security · Spring Data JPA ·
PostgreSQL · Docker Compose · JUnit 5 / Mockito / JaCoCo

## Run locally
Requires Java 17, Maven and Docker.

    docker compose up -d
    mvn spring-boot:run

App: http://localhost:8080

## Test users
| User | Password | Role |
|---|---|---|
| admin | admin123 | ADMIN |
| manager | manager123 | MANAGER |
| user | user123 | USER |

## Tests
    mvn test      # coverage report: target/site/jacoco/index.html

## Project structure
`controller/` web layer · `service/` business logic · `repository/` data
access · `entity/` JPA · `dto/` + `mapper/` data transfer

## Documentation
- `docs/proposal.pdf`: Early Pass proposal
- `docs/ai-usage.md`: AI tools and prompts used

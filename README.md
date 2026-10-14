# Bancosol Lite

Web application to coordinate food-donation campaigns: volunteers register
donations at collection points, managers verify them and follow each
campaign's progress. Course project, Web Application Programming (WUT).

**Status:** work in progress (Early Pass). 

## Stack
Spring Boot · Java · Thymeleaf · Spring Data JPA ·
PostgreSQL · Docker Compose · JUnit  / Mockito / JaCoCo

## Run locally
Requires Java, Maven and Docker.

    docker compose up -d
    mvn spring-boot:run

App: http://localhost:8080

## Test users
| User | Password | Role |
|---|---|---|
| admin | admin123 | ADMIN |
| manager | manager123 | MANAGER |
| user | user123 | USER |


## Project structure
`controller/` web layer · `service/` business logic · `repository/` data
access · `entity/` JPA · `dto/` + `mapper/` data transfer


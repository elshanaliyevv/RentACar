# Rent a Car Application

A robust backend service for a car rental platform built with Spring Boot and Java 21.

## Features

* **User Authentication & Authorization**: Secure login and registration using JSON Web Tokens (JWT) and Spring Security.
* **Car Management**: APIs to manage the fleet of cars (create, read, update, delete).
* **Booking System**: Users can book cars for specific dates.
* **Review/Comment System**: Users can leave comments or reviews on cars.
* **Database Integration**: Utilizes PostgreSQL with Spring Data JPA for persistent storage.
* **Dockerized Database**: Easy database setup using Docker Compose.

## Tech Stack

* **Language**: Java 21
* **Framework**: Spring Boot (WebMVC, Data JPA, Security, Validation)
* **Build Tool**: Gradle
* **Database**: PostgreSQL
* **Security**: Spring Security + JWT
* **Utilities**: Lombok (reduces boilerplate code)
* **Containerization**: Docker Compose (for PostgreSQL)

## Prerequisites

Before running the application, ensure you have the following installed:
* Java 21 JDK
* Docker and Docker Compose
* Gradle (Optional, as the project uses Gradle Wrapper)

## Getting Started

### 1. Start the Database

The project includes a `docker-compose.yml` file to quickly spin up a PostgreSQL instance.

```bash
docker-compose up -d
```
This will start PostgreSQL on port `8081` with the default database name `rentacar`, user `postgres`, and password `postgres`.

### 2. Configure the Application

If you need to change database credentials or JWT secrets, update the `src/main/resources/application.properties` or `application.yml` file.

### 3. Run the Application

You can run the application using the Gradle wrapper:

```bash
# On Mac/Linux
./gradlew bootRun

# On Windows
gradlew.bat bootRun
```

The application will typically start on `http://localhost:8080`.

## Project Structure

* `config/`: Application configuration classes (Security, etc.).
* `controller/`: REST API endpoints.
* `model/`: Entity classes (`Car`, `User`, `Booking`, `Comment`), Requests, and Responses.
* `repository/`: Spring Data JPA repositories for database access.
* `service/`: Business logic implementation.
* `security/`: JWT filters and security-related classes.
* `exception/`: Global exception handling.
* `enums/`: Enumerations used in the project.
* `mapper/`: Object mapping utilities.

## Building the Project

To build the executable JAR file, run:

```bash
./gradlew build
```
The compiled JAR will be located in the `build/libs/` directory.

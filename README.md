
This project demonstrates how to build a basic Spring Boot application following the MVC (Model-View-Controller) pattern including features: get all items, edit an item; with a PostgreSQL database connection. 
It utilizes DTOs (Data Transfer Objects) and MapStruct for mapping data between the client and server, and implements global exception handling to manage errors efficiently. The project also includes integration testing using JUnit to ensure proper functionality.

## Features

- **Spring Boot**: Backend framework for creating RESTful APIs.
- **PostgreSQL**: Database for storing application data.
- **DTO (Data Transfer Object)**: For transferring data between client and server.
- **MapStruct**: Used for mapping between DTOs and entities.
- **Global Exception Handling**: Custom exception handling to manage errors and provide standardized responses.
- **Response Builder**: Utility class for returning structured API responses.
- **PageList**: For paginated data responses.
- **JUnit Testing**: Integration testing to verify API endpoints and service layer functionality.

## Architecture

This project follows the **MVC (Model-View-Controller)** architecture:

- **Model**: Entities representing the application data and business logic.
- **View**: REST API responses that are sent to the client.
- **Controller**: Manages the communication between the Model and the View, receiving requests, and returning appropriate responses.

## Technologies Used

- **Spring Boot**: For building RESTful APIs.
- **PostgreSQL**: For database storage.
- **JPA (Java Persistence API)**: For database interaction and ORM (Object-Relational Mapping).
- **MapStruct**: For mapping between DTOs and entities.
- **JUnit**: For writing and executing tests.
- **Spring Data JPA**: For repository management.
- **Spring Web**: For REST controllers and API handling.

## Setup and Installation

### Prerequisites

- **Java 17+**
- **Maven**: For building the project.
- **PostgreSQL**: Ensure you have a local PostgreSQL database up and running. Create application file to configure your own database connection

### Clone the Repository

1. **Clone the project repository**:
   ```bash
   https://github.com/thoanguyen2907/backend-translator

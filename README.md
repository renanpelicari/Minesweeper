# Minesweeper

## Project Structure

This project follows a clean architecture design, which promotes separation of concerns and modularization.
To avoid cyclical dependencies, the order of access should follow this pattern:

```shell
presentation > business > domain > infrastructure
```

This means that each layer can access the layer to its right, but not the previous one. Objects are transited between layers using Value Objects (VO), Data Transfer Objects (DTO), and Entities, with the help of mappers.

Here is a brief overview of the different layers and their components:

* `business`: Contains the business logic of the application, including use cases and strategies.
* `domain`: Contains the domain logic of the application, including mappers, DTOs, services, and utilities.
* `infrastructure`: Contains infrastructure-related components, such as configuration, exceptions, and repositories. The `repository` directory contains entities specific to the infrastructure layer.
* `presentation`: Contains the presentation layer components, such as controllers (for REST or Thymeleaf) and views (in case of Thymeleaf), along with their associated Value Objects (VO).

By organizing the project structure in this way, we ensure a clear separation of concerns and promote maintainability and extensibility of the codebase.


## Useful links

Actuator:
http://localhost:8080/actuator

Actuator/Health:
http://localhost:8080/actuator/health

OpenAPI Docs:
http://localhost:8080/api-docs

SwaggerUI:
http://localhost:8080/swagger-ui
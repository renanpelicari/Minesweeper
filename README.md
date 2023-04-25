# Minesweeper API

## Project Structure

This project follows a clean architecture design, which promotes separation of concerns and modularization.
To avoid cyclical dependencies, the order of access should follow this pattern:

```shell
presentation > business > domain > infrastructure
```

This means that each layer can access the layer to its right, but not the previous one. Objects are transited between layers using Value Objects (VO), Data Transfer Objects (DTO), and Entities, with the help of mappers.

Here is a brief overview of the different layers and their components:

* `business`: Contains the business logic of the application, including use cases.
* `domain`: Contains the domain logic of the application, including mappers, DTOs, services, and utilities.
* `infrastructure`: Contains infrastructure-related components, such as configuration, exceptions, and repositories. The `repository` directory contains entities specific to the infrastructure layer.
* `presentation`: Contains the presentation layer components, in this case the RestController.

By organizing the project structure in this way, we ensure a clear separation of concerns and promote maintainability and extensibility of the codebase.

----

## Pre-Requirements

1. Java 17
2. Docker and Docker-compose

----

## How to Build

To build the minesweeper API, follow these steps:

```shell
./gradlew build
docker build -t minesweeper-api -f Dockerfile .
```

----

## How to Run

To run the Minesweeper API, use the following command:

```shell
docker-compose up
```

----

## FrontEnd

This project contains a Frontend application to play the game.

[FrontEnd APP README](front/README.md)


----

## Useful links

Actuator:
http://localhost:8088/actuator

Actuator/Health:
http://localhost:8088/actuator/health

OpenAPI Docs:
http://localhost:8088/api-docs

SwaggerUI:
http://localhost:8088/swagger-ui

----

### Accessing the MongoDB:

This project provides the MongoExpress, which you can access by this address:

http://localhost:8081/db/sythub

---

## Author

Renan Peliçari Rodrigues

[renanpelicari@gmail.com](mailto:renanpelicari@gmail.com)

https://www.linkedin.com/in/renanpelicari/

https://github.com/renanpelicari

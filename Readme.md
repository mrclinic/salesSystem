# Spring Boot, MySQL, JPA, Hibernate Rest API

Build Restful CRUD API for system should include products management, client’s
management and sales operations management using Spring Boot, Mysql, JPA and Hibernate.

## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Mysql - 5.x.x

## Steps to Setup

1. unzip the attached file**


2. Create Mysql database using phpmyadmin
create database with the name: salesSystem


3. Change mysql username and password

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password`

4. Build and run the app using maven**


mvn package
java -jar target/SalesSystem.jar


Alternatively, you can run the app without packaging it using -

mvn spring-boot:run


The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

Client Controller
GET /apic/clients
POST /apic/clients
GET /apic/clients/{id}
PUT /apic/clients/{id}
DELETE /apic/clients/{id}

Product Controller
GET /apip/products/getAllProducts
POST /apip/products
GET /apip/products/{id}
PUT /apip/products/{id}
DELETE /apip/products/{id}

Sales Controller
GET /api/clients/{clientId}/sales
POST /api/clients/{clientId}/sales
PUT /api/clients/{clientId}/sales/{salesId}
DELETE /api/clients/{clientId}/sales/{salesId}


Logging:
you can find log file: salesSystem.log in the main directory.

the log message is formed as the following:

date					type	loggerName			function	request information
2021-03-07 17:20:43,246 INFO salesSystemLogger  - updateSales(): Info-->clientId: 2, salesRequest: {"id":0,"seller":"ggg","total":40.0,"createdAt":null,"updatedAt":null}

ERD Diagram:
erdDiagram.PNG in the resources diractory(src/main/resources/)

You can test them using postman or from swagger ui: http://127.0.0.1:8080/swagger-ui.html#/.


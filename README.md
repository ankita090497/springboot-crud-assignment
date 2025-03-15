# springboot-crud-assignment
Spring boot CRUD web application 

# Introduction
This project is a simple web application that demonstrates basic CRUD operations using Spring Boot. It allows users to create, read, update, and delete records in a database.

# Features
- Create new record
- Read specific record by ID
- Update existing record
- Delete record

# Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- MySQL Database
- Maven

# Prerequisites
Before you begin, ensure you have the following installed:

- Java 17 or higher
- Maven

# Setup and Installation

Clone the repository:
- git clone https://github.com/ankita090497/springboot-crud-assignment.git

Navigate to the project directory:
- cd springboot-crud-assignment

Build the project using Maven:
- mvn clean install

Running the Application 
Run the application using Maven:
- mvn spring-boot:run

# API Endpoints
Test below Endpoints using postman

- Create a new record
POST localhost:8091/api/person/add  


  Payload : {
  "firstname": "Priyanka",
  "lastname": "Patel",
  "contact":1234567895,
  "address":"Anand vihar society",
  "city":"Ahmedabad"
  }

- Retrieve a specific record by ID
GET localhost:8091/api/person/fetch/{personId}

- Update an existing record by ID
PUT localhost:8091/api/person/update/{personId}


  Payload : {
  "firstname": "Priyanka",
  "lastname": "Patel",
  "contact":1234567895,
  "address":"Anand vihar society",
  "city":"Ahmedabad"
  }

- Delete a record by ID
DELETE localhost:8091/api/person/delete/{personId}




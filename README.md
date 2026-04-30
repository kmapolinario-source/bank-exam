# Bank Exam

## Tech Stack
- Java 8
- Spring Boot 2.6.15
- H2 Database
- Spring Data JPA

## Features
- Customer Account Creation (POST API)
- Customer Inquiry (GET API)
- Input Validation (400 handling)
- Global Exception Handling (404/500)

## API Endpoints

### Create Account
POST /api/v1/account

### Get Customer
GET /api/v1/account/{customerNumber}

## Database
- H2 In-memory database

## How to Run
1. Clone repo
2. Run Spring Boot application
3. Access:
   - http://localhost:8080/api/v1/account
   - http://localhost:8080/h2-console

## Test Evidence
See `/screenshots` folder

## Author
Candidate Submission
